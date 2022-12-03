package com.web3horizen.web.modules.home.controllers;

import com.web3horizen.web.framework.Request;
import com.web3horizen.web.framework.Response;
import com.web3horizen.web.framework.Session;
import com.web3horizen.web.framework.annotation.Controller;
import com.web3horizen.web.framework.annotation.Get;
import com.web3horizen.web.framework.mvc.Result;
import com.web3horizen.web.framework.mvc.results.Html;
import com.web3horizen.web.framework.mvc.results.Json;
import org.json.JSONObject;

@Controller("/")
public class HomeController extends com.web3horizen.web.framework.mvc.Controller {

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
