package com.web3horizen.web;

import java.io.Serializable;

public abstract class Session implements Serializable {
    private static final ThreadLocal<Session> current = new ThreadLocal<>();

    public final static void set(Session session) {
        current.set(session);
    }

    public final static Session get() {
        return current.get();
    }

    public abstract String getId();
}
