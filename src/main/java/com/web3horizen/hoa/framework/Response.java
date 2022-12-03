package com.web3horizen.hoa.framework;

public abstract class Response {
    public abstract void setContentType(final String mimeType);

    public abstract void write(final String string);

    public abstract void setStatus(int code);

    public abstract void addHeader(String header, String value);
}
