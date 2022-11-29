package com.web3horizen.web.servlet;

import com.web3horizen.web.Application;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebApplicationServlet extends HttpServlet {
    protected final Application app;

    public WebApplicationServlet(Application app) {
        this.app = app;
    }

    protected final void doPost(final HttpServletRequest servletRequest, final HttpServletResponse servletResponse) {
        doGet(servletRequest, servletResponse);
    }

    protected final void doGet(final HttpServletRequest servletRequest, final HttpServletResponse servletResponse) {
        HttpSession session = HttpSession.getSession(servletRequest);

        app.handleRequest(session, new HttpRequest(servletRequest), new HttpResponse(servletResponse));

        HttpSession.set(null);
    }
}
