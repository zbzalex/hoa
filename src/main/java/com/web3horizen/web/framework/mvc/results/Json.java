package com.web3horizen.web.framework.mvc.results;

import com.web3horizen.web.framework.Request;
import com.web3horizen.web.framework.Response;
import com.web3horizen.web.framework.mvc.Result;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;

public class Json extends Result {
    private final JSONObject obj;

    public Json(JSONObject obj) {
        this.obj = obj;
    }

    @Override
    public void apply(Request request, Response response) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json; charset=utf-8");
        response.write(obj.toString());
    }
}
