package com.web3horizen.web.framework.mvc.results;

import com.web3horizen.web.framework.Request;
import com.web3horizen.web.framework.Response;
import com.web3horizen.web.framework.mvc.Result;

import javax.servlet.http.HttpServletResponse;

public class InternalError extends Result {
    @Override
    public void apply(Request request, Response response) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
