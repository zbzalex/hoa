package com.web3horizen.hoa.example.modules.user.controllers;

import com.web3horizen.hoa.framework.Request;
import com.web3horizen.hoa.framework.Response;
import com.web3horizen.hoa.framework.Session;
import com.web3horizen.hoa.framework.annotation.Controller;
import com.web3horizen.hoa.framework.annotation.Get;
import com.web3horizen.hoa.framework.mvc.Result;
import com.web3horizen.hoa.framework.mvc.results.Html;

@Controller("/user")
public class UserController extends com.web3horizen.hoa.framework.mvc.Controller {

    public UserController(Session session, Request req, Response res) {
        super(session, req, res);
    }

    @Get("/")
    public Result actionIndex() {
        return new Html("This is user controller");
    }

    @Get("/profile")
    public Result actionProfile() {
        return new Html("This is user profile");
    }
}
