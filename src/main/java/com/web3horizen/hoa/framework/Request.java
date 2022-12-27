package com.web3horizen.hoa.framework;

import java.util.Map;

public abstract class Request {
    public abstract String getUrl();
    public abstract String getPath();
    public abstract String getMethod();
    public abstract Map<String, String> getParameterMap();
    public abstract String getParameter(final String key);
    public abstract String[] getParameters(final String key);
    public abstract Session getSession();
    public abstract void setSession(Session session);
    public abstract Map<String, String> getAttributes();
    public abstract void setAttributes(Map<String, String> attributes);
}
