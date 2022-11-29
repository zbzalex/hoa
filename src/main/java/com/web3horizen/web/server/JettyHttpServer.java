package com.web3horizen.web.server;

import com.web3horizen.web.Application;
import com.web3horizen.web.HttpServer;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.jetty.servlet.ServletHolder;
import org.mortbay.jetty.servlet.SessionHandler;

public class JettyHttpServer implements HttpServer {
    private final int port;

    public JettyHttpServer(int port) {
        this.port = port;
    }

    public void start(Application app) {
        try {
            Server server = new Server(port);

            SessionHandler sessionHandler = new SessionHandler();

            server.setHandler(sessionHandler);

            ServletHandler servletHandler = new ServletHandler();
            servletHandler.addServletWithMapping(new ServletHolder(app.getServlet()), "/*");
            sessionHandler.addHandler(servletHandler);

            server.start();
            System.out.println("Listening port " + port);

            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
