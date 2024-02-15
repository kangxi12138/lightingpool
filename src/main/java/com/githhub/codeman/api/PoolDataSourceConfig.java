package com.githhub.codeman.api;


import com.githhub.codeman.connection.IPoolConnection;



public interface PoolDataSourceConfig extends DataSourceConfig {

    void returnConnection(IPoolConnection pooledConnection);


    void setMinSize(final int minSize);


    void setMaxSize(final int maxSize);


    void setMaxWaitMills(final long maxWaitMills);

    void setValidQuery(final String validQuery);


    void setValidTimeOutSeconds(final int validTimeOutSeconds);


    void setTestOnBorrow(final boolean testOnBorrow);


    void setTestOnReturn(final boolean testOnReturn);


    void setTestOnIdle(final boolean testOnIdle);


    void setTestOnIdleIntervalSeconds(final long testOnIdleIntervalSeconds);

}
