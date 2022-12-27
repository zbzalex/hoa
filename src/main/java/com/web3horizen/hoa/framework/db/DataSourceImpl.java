package com.web3horizen.hoa.framework.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceImpl implements DataSource {
    protected String url;
    protected String username;
    protected String password;

    public DataSourceImpl(String url,
                          String username,
                          String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            return null;
        }
    }
}
