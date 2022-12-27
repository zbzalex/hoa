package com.web3horizen.hoa.framework.server;

import com.web3horizen.hoa.framework.HttpServer;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.jetty.servlet.ServletHolder;
import org.mortbay.jetty.servlet.SessionHandler;

import javax.servlet.http.HttpServlet;

public class JettyHttpServer implements HttpServer {
    private final int port;

    public JettyHttpServer(int port) {
        this.port = port;
    }

    public void start(HttpServlet servlet) {
        try {
            Server server = new Server(port);

            SessionHandler sessionHandler = new SessionHandler();
            server.setHandler(sessionHandler);

            ServletHandler servletHandler = new ServletHandler();
            servletHandler.addServletWithMapping(new ServletHolder(servlet), "/*");
            sessionHandler.addHandler(servletHandler);

            server.start();

            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
