package com.web3horizen.web;

public abstract class Response {
    public abstract void setContentType(final String mimeType);
    public abstract void write(final String string);
}
