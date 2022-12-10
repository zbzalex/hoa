package com.web3horizen.hoa.framework.db.annotation;

public @interface Column {
    String value();

    boolean nullable();

    String defaultValue();
}
