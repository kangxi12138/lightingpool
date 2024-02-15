package com.githhub.codeman.builder;


import com.githhub.codeman.constant.DriverType;
import com.githhub.codeman.constant.PoolConstants;
import com.githhub.codeman.datasource.PoolDataSource;
import com.githhub.codeman.datasource.PrototypePoolDataSource;

import javax.sql.DataSource;


public class PoolBuilder {

    
    private String driverClass = DriverType.MYSQL_8;

    private String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";

    private String username = "root";

    private String password = "1234";

    
    private int minSize = PoolConstants.DEFAULT_MIN_SIZE;

    
    private int maxSize = PoolConstants.DEFAULT_MAX_SIZE;

    
    private long maxWaitMills = PoolConstants.DEFAULT_MAX_WAIT_MILLS;

    
    private String validQuery = PoolConstants.DEFAULT_VALID_QUERY;

    
    private int validTimeOutSeconds = PoolConstants.DEFAULT_VALID_TIME_OUT_SECONDS;

    
    private boolean testOnBorrow = PoolConstants.DEFAULT_TEST_ON_BORROW;

    
    private boolean testOnReturn = PoolConstants.DEFAULT_TEST_ON_RETURN;

    
    private boolean testOnIdle = PoolConstants.DEFAULT_TEST_ON_IDLE;

    
    private long testOnIdleIntervalSeconds = PoolConstants.DEFAULT_TEST_ON_IDLE_INTERVAL_SECONDS;

    public PoolBuilder driverClass(String driverClass) {
        this.driverClass = driverClass;
        return this;
    }

    public PoolBuilder url(String url) {
        this.url = url;
        return this;
    }

    public PoolBuilder username(String username) {
        this.username = username;
        return this;
    }

    public PoolBuilder password(String password) {
        this.password = password;
        return this;
    }

    public PoolBuilder minSize(int minSize) {
        this.minSize = minSize;
        return this;
    }

    public PoolBuilder maxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    public PoolBuilder maxWaitMills(long maxWaitMills) {
        this.maxWaitMills = maxWaitMills;
        return this;
    }

    public PoolBuilder validQuery(String validQuery) {
        this.validQuery = validQuery;
        return this;
    }

    public PoolBuilder validTimeOutSeconds(int validTimeOutSeconds) {
        this.validTimeOutSeconds = validTimeOutSeconds;
        return this;
    }

    public PoolBuilder checkOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
        return this;
    }

    public PoolBuilder checkOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
        return this;
    }

    public PoolBuilder checkOnIdle(boolean testOnIdle) {
        this.testOnIdle = testOnIdle;
        return this;
    }

    public PoolBuilder checkOnIdleIntervalSeconds(long testOnIdleIntervalSeconds) {
        this.testOnIdleIntervalSeconds = testOnIdleIntervalSeconds;
        return this;
    }

    public static PoolBuilder newBuilder() {
        return new PoolBuilder();
    }

    
    public DataSource getPool() {
        PoolDataSource dataSource = new PoolDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);

        dataSource.setMinSize(minSize);
        dataSource.setMaxSize(maxSize);
        dataSource.setMaxWaitMills(maxWaitMills);

        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnIdle(testOnIdle);
        dataSource.setTestOnIdleIntervalSeconds(testOnIdleIntervalSeconds);
        dataSource.setTestOnReturn(testOnReturn);

        dataSource.setValidQuery(validQuery);
        dataSource.setValidTimeOutSeconds(validTimeOutSeconds);

        dataSource.init();
        return dataSource;
    }


    public DataSource getPrototype() {
        PrototypePoolDataSource dataSource = new PrototypePoolDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        return dataSource;
    }

}
