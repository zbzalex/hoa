package com.web3horizen.hoa.framework.db;

import java.sql.Connection;

public interface DataSource {
    Connection getConnection();
}
