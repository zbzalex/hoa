package com.web3horizen.hoa.framework;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest extends Request {
    protected final HttpServletRequest httpServletRequest;
    protected Session session;
    protected Map<String, String> attributes = new HashMap<>();

    public HttpRequest(final HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public Cookie[] getCookies() {
        return httpServletRequest.getCookies();
    }

    @Override
    public String getUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(httpServletRequest.getPathInfo());
        String queryString = httpServletRequest.getQueryString();
        if (queryString != null) {
            sb.append("?");
            sb.append(queryString);
        }

        return sb.toString();
    }

    public String getPath() {
        return httpServletRequest.getPathInfo();
    }

    public String getMethod() {
        return httpServletRequest.getMethod();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Map<String, String> getParameterMap() {
        final Map<String, String> parameterMap = new HashMap<>();
        for (Enumeration enumeration = httpServletRequest.getParameterNames();
             enumeration.hasMoreElements(); ) {
            final String name = (String) enumeration.nextElement();
            parameterMap.put(name, httpServletRequest.getParameter(name));
        }

        return parameterMap;
    }

    @Override
    public String getParameter(String key) {
        return httpServletRequest.getParameter(key);
    }

    @Override
    public String[] getParameters(String key) {
        return httpServletRequest.getParameterValues(key);
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
