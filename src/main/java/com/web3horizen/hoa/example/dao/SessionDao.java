package com.web3horizen.hoa.example.dao;

import com.web3horizen.hoa.example.entities.Session;
import com.web3horizen.hoa.framework.db.annotation.Dao;
import com.web3horizen.hoa.framework.db.annotation.Query;

import java.util.List;

@Dao
public interface SessionDao {
    @Query("select * from sessions;")
    List<Session> getAll();
}
