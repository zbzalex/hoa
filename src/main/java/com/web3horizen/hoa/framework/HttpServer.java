package com.web3horizen.hoa.framework;

import javax.servlet.http.HttpServlet;

public interface HttpServer {
    void start(HttpServlet servlet);
}
