package com.web3horizen.web.framework.servlet;

import com.web3horizen.web.framework.Request;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest extends Request {
    private final HttpServletRequest httpServletRequest;

    public HttpRequest(final HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public Cookie[] getCookies() {
        return httpServletRequest.getCookies();
    }

    @Override
    public String getUrl() {
        String url = httpServletRequest.getPathInfo();
        String queryString = httpServletRequest.getQueryString();
        if (queryString != null) {
            url += "?" + queryString;
        }

        return url;
    }

    public String getPath() {
        return httpServletRequest.getPathInfo();
    }

    public String getMethod() {
        return httpServletRequest.getMethod();
    }

    @Override
    public Map<String, String> getParameterMap() {
        final Map<String, String> parameterMap = new HashMap<>();
        for (Enumeration enumeration = httpServletRequest.getParameterNames(); enumeration.hasMoreElements(); ) {
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
}
