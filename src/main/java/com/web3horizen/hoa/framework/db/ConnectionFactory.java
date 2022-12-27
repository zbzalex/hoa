package com.web3horizen.hoa.framework.db;

import java.sql.Connection;

public class ConnectionFactory {
    public Connection getConnection(DataSource dataSource) {
        return dataSource.getConnection();
    }
}
