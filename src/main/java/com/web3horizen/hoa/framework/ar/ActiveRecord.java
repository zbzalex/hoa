package com.web3horizen.hoa.framework.ar;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ActiveRecord<T> {
    public static Connection CONNECTION;

    public void save() {
    }

    public void delete() {
    }

    public List<T> find() {
        List<T> list = new ArrayList<>();

        return list;
    }

    public T findOne() {
        return null;
    }
}
