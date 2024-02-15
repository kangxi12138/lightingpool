package com.githhub.codeman.datasource;


public abstract class AbstractDataSourceConfig extends DataSourceConfigAdaptor {

    
    protected String driverClass;

    
    protected String jdbcUrl;

    
    protected String user;

    
    protected String password;

    public String getDriverClass() {
        return driverClass;
    }

    @Override
    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    @Override
    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUser() {
        return user;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
