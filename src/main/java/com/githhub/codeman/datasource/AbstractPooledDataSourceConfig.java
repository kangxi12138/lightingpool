package com.githhub.codeman.datasource;


import com.githhub.codeman.api.PoolLifeCycle;
import com.githhub.codeman.api.PoolDataSourceConfig;
import com.githhub.codeman.constant.PoolConstants;

public abstract class AbstractPooledDataSourceConfig extends AbstractDataSourceConfig
        implements PoolDataSourceConfig, PoolLifeCycle {

    
    protected int minSize = PoolConstants.DEFAULT_MIN_SIZE;

    
    protected int maxSize = PoolConstants.DEFAULT_MAX_SIZE;

    
    protected long maxWaitMills = PoolConstants.DEFAULT_MAX_WAIT_MILLS;

    
    protected String validQuery = PoolConstants.DEFAULT_VALID_QUERY;

    
    protected int validTimeOutSeconds = PoolConstants.DEFAULT_VALID_TIME_OUT_SECONDS;

    
    protected boolean testOnBorrow = PoolConstants.DEFAULT_TEST_ON_BORROW;

    
    protected boolean testOnReturn = PoolConstants.DEFAULT_TEST_ON_RETURN;

    
    protected boolean testOnIdle = PoolConstants.DEFAULT_TEST_ON_IDLE;

    
    protected long testOnIdleIntervalSeconds = PoolConstants.DEFAULT_TEST_ON_IDLE_INTERVAL_SECONDS;

    public int getMinSize() {
        return minSize;
    }

    @Override
    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public long getMaxWaitMills() {
        return maxWaitMills;
    }

    @Override
    public void setMaxWaitMills(long maxWaitMills) {
        this.maxWaitMills = maxWaitMills;
    }

    public String getValidQuery() {
        return validQuery;
    }

    @Override
    public void setValidQuery(String validQuery) {
        this.validQuery = validQuery;
    }

    public int getValidTimeOutSeconds() {
        return validTimeOutSeconds;
    }

    @Override
    public void setValidTimeOutSeconds(int validTimeOutSeconds) {
        this.validTimeOutSeconds = validTimeOutSeconds;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    @Override
    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    @Override
    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestOnIdle() {
        return testOnIdle;
    }

    @Override
    public void setTestOnIdle(boolean testOnIdle) {
        this.testOnIdle = testOnIdle;
    }

    public long getTestOnIdleIntervalSeconds() {
        return testOnIdleIntervalSeconds;
    }

    @Override
    public void setTestOnIdleIntervalSeconds(long testOnIdleIntervalSeconds) {
        this.testOnIdleIntervalSeconds = testOnIdleIntervalSeconds;
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

}
