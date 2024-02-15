package com.githhub.codeman.datasource;


import com.githhub.codeman.util.DriverClassUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class PrototypePoolDataSource extends AbstractDataSourceConfig {

    @Override
    public Connection getConnection() throws SQLException {
        DriverClassUtil.loadDriverClass(super.driverClass, super.jdbcUrl);

        return DriverManager.getConnection(super.getJdbcUrl(),
                super.getUser(), super.getPassword());
    }
}
