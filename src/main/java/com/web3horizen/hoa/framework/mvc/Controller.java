package com.web3horizen.hoa.framework.mvc;


import com.web3horizen.hoa.framework.Request;
import com.web3horizen.hoa.framework.Response;

public abstract class Controller {
    protected final Request request;
    protected final Response response;

    public Controller(Request request, Response response) {
        this.request = request;
        this.response = response;
    }
}
