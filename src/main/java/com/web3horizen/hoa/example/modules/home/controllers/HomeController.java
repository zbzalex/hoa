package com.web3horizen.hoa.example.modules.home.controllers;

import com.web3horizen.hoa.example.dto.SessionCreatedDto;
import com.web3horizen.hoa.framework.Request;
import com.web3horizen.hoa.framework.Response;
import com.web3horizen.hoa.framework.annotation.Controller;
import com.web3horizen.hoa.framework.annotation.RequestMapping;
import com.web3horizen.hoa.framework.mvc.Result;
import com.web3horizen.hoa.framework.mvc.results.Html;
import com.web3horizen.hoa.framework.mvc.results.Json;

@Controller("/")
public class HomeController extends com.web3horizen.hoa.framework.mvc.Controller {
    //private final Logger logger = LoggerFactory.getLogger(getClass());

    public HomeController(Request req, Response res) {
        super(req, res);
    }

    @RequestMapping("/")
    public Result actionIndex() {
        return new Html("hello");
    }

    @RequestMapping("/json/@{id}")
    public Result actionJson() throws InstantiationException, IllegalAccessException {
        return new Json(new SessionCreatedDto("hello, " + request.getAttributes().get("id")));
    }
}
