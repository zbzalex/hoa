package com.web3horizen.web.servlet;

import com.web3horizen.web.Response;

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
}
