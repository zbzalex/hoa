package com.web3horizen.hoa.example.modules.home.controllers;

import com.web3horizen.hoa.framework.Request;
import com.web3horizen.hoa.framework.Response;
import com.web3horizen.hoa.framework.Session;
import com.web3horizen.hoa.framework.annotation.Controller;
import com.web3horizen.hoa.framework.annotation.Get;
import com.web3horizen.hoa.framework.mvc.Result;
import com.web3horizen.hoa.framework.mvc.results.Html;
import com.web3horizen.hoa.framework.mvc.results.Json;
import org.json.JSONObject;

@Controller("/")
public class HomeController extends com.web3horizen.hoa.framework.mvc.Controller {

    public HomeController(Session session, Request req, Response res) {
        super(session, req, res);
    }

    @Get("/")
    public Result actionIndex() {
        JSONObject obj = new JSONObject();
        obj.append("helloPage", "http://localhost:18080/hello");

        return new Json(obj);
    }

    @Get("/hello")
    public Result actionHello() {
        return new Html("hello page");
    }
}
