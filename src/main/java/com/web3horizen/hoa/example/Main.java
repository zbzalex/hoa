package com.web3horizen.hoa.example;

import com.web3horizen.hoa.example.app.AppModule;
import com.web3horizen.hoa.framework.Application;
import com.web3horizen.hoa.framework.WebApplication;
import com.web3horizen.hoa.framework.server.JettyHttpServer;

import javax.servlet.http.HttpServlet;
import java.util.Properties;

public class Main {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws Exception {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Properties properties = new Properties();
        properties.load(Main.class.getClassLoader().getResourceAsStream("config.properties"));
        int port = Integer.parseInt((String) properties.get("server.port"));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JettyHttpServer server = new JettyHttpServer(port);

        Application app = new WebApplication(AppModule.class);
        app.initModule();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        server.start((HttpServlet) app);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
}
