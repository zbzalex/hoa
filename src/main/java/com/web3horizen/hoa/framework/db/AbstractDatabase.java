package com.web3horizen.hoa.framework.db;

import java.sql.Connection;

public class AbstractDatabase {
    protected final Connection connection;

    public AbstractDatabase(Connection connection) {
        this.connection = connection;
    }
}
