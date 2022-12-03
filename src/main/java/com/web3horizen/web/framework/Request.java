package com.web3horizen.web.framework;

import java.util.Map;

public abstract class Request {
    public abstract String getUrl();

    public abstract String getPath();

    public abstract String getMethod();

    public abstract Map<String, String> getParameterMap();

    public abstract String getParameter(final String key);

    public abstract String[] getParameters(final String key);

}
