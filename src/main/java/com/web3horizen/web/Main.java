package com.web3horizen.web;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.web3horizen.web.server.JettyHttpServer;

import java.util.Properties;

public class Main {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws Exception {

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Properties properties = new Properties();
        properties.load(Main.class.getClassLoader().getResourceAsStream("config.properties"));

        int port = Integer.valueOf((String) properties.get("server.port"));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        JettyHttpServer server = new JettyHttpServer(port);

        Application app = new WebApplication();
        app.any("/", HomeController.class, "index");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        server.start(app);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static class HomeController extends Controller {
        public HomeController(Session session, Request request, Response response) {
            super(session, request, response);
        }

        public void index() {
            JsonObject obj = new JsonObject();
            obj.add("github", new JsonPrimitive("https://github.com/zbzalex"));
            sendJson(obj);
        }
    }
}
