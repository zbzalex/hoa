package com.web3horizen.web;

import com.google.gson.JsonObject;

public abstract class Controller {
    protected final Session session;
    protected final Request request;
    protected final Response response;

    public Controller(Session session, Request request, Response response) {
        this.session = session;
        this.request = request;
        this.response = response;
    }

    protected void sendJson(JsonObject object) {
        response.setContentType("application/json");
        response.write(object.toString());
    }
}
