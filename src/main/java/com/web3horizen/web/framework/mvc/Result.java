package com.web3horizen.web.framework.mvc;

import com.web3horizen.web.framework.Request;
import com.web3horizen.web.framework.Response;

public abstract class Result {

    public abstract void apply(Request request, Response response);
}
