package com.web3horizen.hoa.framework.mvc;


import com.web3horizen.hoa.framework.Request;
import com.web3horizen.hoa.framework.Response;
import com.web3horizen.hoa.framework.Session;

public abstract class Controller {
    protected final Session session;
    protected final Request req;
    protected final Response res;

    public Controller(Session session, Request req, Response res) {
        this.session = session;
        this.req = req;
        this.res = res;
    }
}
