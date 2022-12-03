package com.web3horizen.hoa.framework.mvc.results;

import com.web3horizen.hoa.framework.Request;
import com.web3horizen.hoa.framework.Response;
import com.web3horizen.hoa.framework.mvc.Result;

import javax.servlet.http.HttpServletResponse;

public class Html extends Result {
    private final String text;

    public Html(String text) {
        this.text = text;
    }

    @Override
    public void apply(Request request, Response response) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html; charset=utf-8");
        response.write(text);
    }
}
