package com.web3horizen.hoa.framework;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpResponse extends Response {
    private final HttpServletResponse httpServletResponse;

    public HttpResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    @Override
    public void setContentType(String mimeType) {
        httpServletResponse.setContentType(mimeType);
    }

    @Override
    public void write(String string) {
        try {
            httpServletResponse.getWriter().write(string);
        } catch (IOException e) {
        }
    }

    public void setStatus(int code) {
        httpServletResponse.setStatus(code);
    }

    public void addHeader(String header, String value) {
        httpServletResponse.addHeader(header, value);
    }

    public void addCookie(Cookie cookie) {
        httpServletResponse.addCookie(cookie);
    }
}
