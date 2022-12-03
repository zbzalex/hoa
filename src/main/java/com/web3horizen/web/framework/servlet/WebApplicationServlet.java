package com.web3horizen.web.framework.servlet;

import com.web3horizen.web.framework.Application;
import com.web3horizen.web.framework.mvc.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebApplicationServlet extends HttpServlet {
    protected final Application app;

    public WebApplicationServlet(Application app) {
        this.app = app;
    }

    @Override
    protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        HttpSession session = HttpSession.getSession(servletRequest);
        HttpRequest req = new HttpRequest(servletRequest);
        HttpResponse res = new HttpResponse(servletResponse);

        app.initModule();

        Result result = app.handleRequest(session, req, res);
        result.apply(req, res);
    }
}
