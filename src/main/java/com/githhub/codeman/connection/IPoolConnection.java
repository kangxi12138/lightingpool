package com.githhub.codeman.connection;


import com.githhub.codeman.api.PoolDataSourceConfig;

import java.sql.Connection;


public interface IPoolConnection extends Connection {



    boolean isBusy();


    void setBusy(boolean busy);


    Connection getConnection();


    void setConnection(Connection connection);


    void setDataSource(final PoolDataSourceConfig dataSource);


    PoolDataSourceConfig getDataSource();

}
