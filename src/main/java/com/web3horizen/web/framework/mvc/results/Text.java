package com.web3horizen.web.framework.mvc.results;

import com.web3horizen.web.framework.Request;
import com.web3horizen.web.framework.Response;
import com.web3horizen.web.framework.mvc.Result;

import javax.servlet.http.HttpServletResponse;

public class Text extends Result {
    private final String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void apply(Request request, Response response) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain; charset=utf-8");
        response.write(text);
    }
}
