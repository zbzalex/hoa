package com.web3horizen.hoa.framework.mvc.results;

import com.web3horizen.hoa.framework.Request;
import com.web3horizen.hoa.framework.Response;
import com.web3horizen.hoa.framework.mvc.Result;

import javax.servlet.http.HttpServletResponse;

public class Ok extends Result {
    @Override
    public void apply(Request request, Response response) {
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
