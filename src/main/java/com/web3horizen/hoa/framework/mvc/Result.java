package com.web3horizen.hoa.framework.mvc;

import com.web3horizen.hoa.framework.Request;
import com.web3horizen.hoa.framework.Response;

public abstract class Result {

    public abstract void apply(Request request, Response response);
}
