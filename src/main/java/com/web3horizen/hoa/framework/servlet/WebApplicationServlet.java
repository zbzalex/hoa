package com.web3horizen.hoa.framework.servlet;

import com.web3horizen.hoa.framework.Application;
import com.web3horizen.hoa.framework.HttpRequest;
import com.web3horizen.hoa.framework.HttpResponse;
import com.web3horizen.hoa.framework.HttpSession;
import com.web3horizen.hoa.framework.mvc.Result;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebApplicationServlet extends HttpServlet {
    private final Application context;

    public WebApplicationServlet(Application context) {
        this.context = context;
    }

    protected void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        HttpSession session = HttpSession.getSession(servletRequest);
        HttpRequest request = new HttpRequest(servletRequest);
        HttpResponse response = new HttpResponse(servletResponse);

        Result result = context.handleRequest(session, request, response);
        result.apply(request, response);

        context.terminate();
    }
}
