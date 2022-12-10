package com.web3horizen.hoa.example.entities;

import com.web3horizen.hoa.framework.db.annotation.Entity;
import com.web3horizen.hoa.framework.db.annotation.PrimaryKey;

@Entity
public class Session {
    @PrimaryKey
    public int id;

    public int playerId;

    public String token;

    public String ip;

    public String userAgent;
}
