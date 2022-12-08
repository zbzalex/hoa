package com.web3horizen.hoa.example.modules.home.controllers;

import com.web3horizen.hoa.example.dtos.SessionCreatedDto;
import com.web3horizen.hoa.framework.Request;
import com.web3horizen.hoa.framework.Response;
import com.web3horizen.hoa.framework.Session;
import com.web3horizen.hoa.framework.annotation.Controller;
import com.web3horizen.hoa.framework.annotation.RequestMapping;
import com.web3horizen.hoa.framework.mvc.Result;
import com.web3horizen.hoa.framework.mvc.results.Html;
import com.web3horizen.hoa.framework.mvc.results.Json;

@Controller("/")
public class HomeController extends com.web3horizen.hoa.framework.mvc.Controller {

    public HomeController(Session session, Request req, Response res) {
        super(session, req, res);
    }

    @RequestMapping("/")
    public Result actionIndex() {
        return new Html("hello");
    }

    @RequestMapping("/json")
    public Result actionJson() throws InstantiationException, IllegalAccessException {
        return new Json(new SessionCreatedDto("hello"));
    }
}
