package com.web3horizen.hoa.framework.servlet;

import com.web3horizen.hoa.framework.Session;

import javax.servlet.http.HttpServletRequest;

public class HttpSession extends Session {
    protected transient javax.servlet.http.HttpSession httpServletSession;

    protected HttpSession(javax.servlet.http.HttpSession httpServletSession) {
        this.httpServletSession = httpServletSession;
    }

    public static HttpSession getSession(final HttpServletRequest httpServletRequest) {

        final javax.servlet.http.HttpSession httpServletSession = httpServletRequest.getSession(true);
        HttpSession session = (HttpSession) httpServletSession.getAttribute("session");

        if (session == null) {
            session = new HttpSession(httpServletSession);
            httpServletSession.setAttribute("session", session);
        } else {
            session.httpServletSession = httpServletSession;
        }

        set(session);

        return session;
    }

    public javax.servlet.http.HttpSession getHttpServletSession() {
        return httpServletSession;
    }

    public String getId() {
        return httpServletSession.getId();
    }
}