package com.web3horizen.web.framework;

import java.io.Serializable;

public abstract class Session implements Serializable {
    private static final ThreadLocal<Session> current = new ThreadLocal<>();

    public static void set(Session session) {
        current.set(session);
    }

    public static Session get() {
        return current.get();
    }

    public abstract String getId();
}
