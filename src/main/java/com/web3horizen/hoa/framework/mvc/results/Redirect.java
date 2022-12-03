package com.web3horizen.hoa.framework.mvc.results;

import com.web3horizen.hoa.framework.Request;
import com.web3horizen.hoa.framework.Response;
import com.web3horizen.hoa.framework.mvc.Result;

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
