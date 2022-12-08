package com.web3horizen.hoa.example.models;

import com.web3horizen.hoa.framework.ar.ActiveRecord;
import com.web3horizen.hoa.framework.ar.annotations.PrimaryKey;

import java.util.List;

public class Session extends ActiveRecord<Session> {
    @PrimaryKey
    public int id;

    public int playerId;

    public String token;

    public String ip;

    public String userAgent;

    public List<Session> getSessions() {
        Session session = new Session();

        return session.find();
    }
}
