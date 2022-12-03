package com.web3horizen.web.framework.mvc.results;

import com.web3horizen.web.framework.Request;
import com.web3horizen.web.framework.Response;
import com.web3horizen.web.framework.mvc.Result;

import javax.servlet.http.HttpServletResponse;

public class Redirect extends Result {
    private final String url;

    public Redirect(String url) {
        this.url = url;
    }

    @Override
    public void apply(Request request, Response response) {
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        response.addHeader("Location", url);
    }
}
