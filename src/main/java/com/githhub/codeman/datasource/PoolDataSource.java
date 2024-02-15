package com.githhub.codeman.datasource;


import com.githhub.codeman.connection.IPoolConnection;
import com.githhub.codeman.connection.PoolConnection;
import com.githhub.codeman.exception.JdbcPoolException;
import com.githhub.codeman.util.DriverClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class PoolDataSource extends AbstractPooledDataSourceConfig {

    private static final Logger LOG = LoggerFactory.getLogger(PoolDataSource.class);

    
    private List<IPoolConnection> pool = new ArrayList<>();

    @Override
    public synchronized void init() {
        DriverClassUtil.loadDriverClass(super.driverClass, super.jdbcUrl);

        this.initPool();

        // 初始化 idle check
        this.initIdleCheck();
    }

    @Override
    public synchronized Connection getConnection() throws SQLException {
        //1. 获取第一个不是 busy 的连接
        Optional<IPoolConnection> connectionOptional = getFreeConnectionFromPool();
        if(connectionOptional.isPresent()) {
            return connectionOptional.get();
        }

        //2. 考虑是否可以扩容
        if(pool.size() >= maxSize) {
            //2.1 立刻返回
            if(maxWaitMills <= 0) {
                throw new JdbcPoolException("Can't get connection from pool!");
            }


            //2.2 循环等待
            final long startWaitMills = System.currentTimeMillis();
            final long endWaitMills = startWaitMills + maxWaitMills;
            while (System.currentTimeMillis() < endWaitMills) {
                Optional<IPoolConnection> optional = getFreeConnectionFromPool();
                if(optional.isPresent()) {
                    return optional.get();
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                LOG.debug("等待连接池归还，wait for 1 mills");
            }

            //2.3 等待超时
            throw new JdbcPoolException("Can't get connection from pool, wait time out for mills: " + maxWaitMills);
        }

        //3. 扩容（暂时只扩容一个）
        LOG.debug("开始扩容连接池大小，step: 1");
        IPoolConnection pooledConnection = createPooledConnection();
        pooledConnection.setBusy(true);
        this.pool.add(pooledConnection);
        LOG.debug("从扩容后的连接池中获取连接");
        return pooledConnection;
    }

    @Override
    public void returnConnection(IPoolConnection pooledConnection) {
        // 验证状态
        if(testOnReturn) {
            checkValid(pooledConnection);
        }

        // 设置为不繁忙
        pooledConnection.setBusy(false);
        LOG.debug("归还连接，状态设置为不繁忙");
    }

    
    private Optional<IPoolConnection> getFreeConnectionFromPool() {
        for(IPoolConnection pc : pool) {
            if(!pc.isBusy()) {
                pc.setBusy(true);
                LOG.debug("从连接池中获取连接");

                // 验证有效性
                if(testOnBorrow) {
                    LOG.debug("Test on borrow start");
                    checkValid(pc);
                    LOG.debug("Test on borrow finish");
                }

                return Optional.of(pc);
            }
        }
        // 空
        return Optional.empty();
    }


    
    private void checkValid(final IPoolConnection pooledConnection) {
        if(!(super.validQuery==null||super.validQuery.equals(""))){
            Connection connection = pooledConnection.getConnection();
            try {
                // 如果连接无效，重新申请一个新的替代
                if(!connection.isValid(super.validTimeOutSeconds)) {
                    LOG.debug("Old connection is inValid, start create one for it.");

                    Connection newConnection = createConnection();
                    pooledConnection.setConnection(newConnection);
                    LOG.debug("Old connection is inValid, finish create one for it.");
                }
            } catch (SQLException throwables) {
                throw new JdbcPoolException(throwables);
            }
        } else {
            LOG.debug("valid query is empty, ignore valid.");
        }
    }

    
    private void initPool() {
        final int minSize = super.minSize;
        pool = new ArrayList<>(minSize);

        for(int i = 0; i < minSize; i++) {
            IPoolConnection pooledConnection = createPooledConnection();

            pool.add(pooledConnection);
        }
    }

    
    private IPoolConnection createPooledConnection() {
        Connection connection = createConnection();

        IPoolConnection pooledConnection = new PoolConnection();
        pooledConnection.setBusy(false);
        pooledConnection.setConnection(connection);
        pooledConnection.setDataSource(this);

        return pooledConnection;
    }

    
    private Connection createConnection() {
        try {
            return DriverManager.getConnection(super.getJdbcUrl(),
                    super.getUser(), super.getPassword());
        } catch (SQLException e) {
            throw new JdbcPoolException(e);
        }
    }


    
    private void initIdleCheck() {
        if(!(super.validQuery==null||super.validQuery.equals(""))) {
            ScheduledExecutorService idleExecutor = Executors.newSingleThreadScheduledExecutor();

            idleExecutor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    testOnIdleCheck();
                }
            }, super.testOnIdleIntervalSeconds, testOnIdleIntervalSeconds, TimeUnit.SECONDS);
            LOG.debug("Test on idle config with interval seonds: " + testOnIdleIntervalSeconds);
        }
    }

    
    private void testOnIdleCheck() {
        LOG.debug("start check test on idle");
        for(IPoolConnection pc : this.pool) {
            if(!pc.isBusy()) {
                checkValid(pc);
            }
        }
        LOG.debug("finish check test on idle");
    }


}
