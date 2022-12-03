package com.web3horizen.web.framework;

import javax.servlet.http.HttpServlet;

public interface HttpServer {
    void start(HttpServlet servlet);
}
