package com.web3horizen.hoa.framework;

import com.web3horizen.hoa.framework.mvc.Result;
import com.web3horizen.hoa.framework.server.JettyHttpServer;
import com.web3horizen.hoa.framework.servlet.WebApplicationServlet;

import java.util.List;

public class WebApplication implements Application {
    private HttpServer server = new JettyHttpServer(18080);
    private RequestHandler requestHandler;

    public WebApplication() {
    }

    public WebApplication(HttpServer server) {
        this.server = server;
    }

    public void init(Class<?> mainModuleClass) {
        WebApplicationAnnotationRuntimeProcessor processor = new WebApplicationAnnotationRuntimeProcessor();
        List<Route> routes = processor.processModule(mainModuleClass);
        requestHandler = new RequestHandler(routes);
    }

    public Result handleRequest(Request request, Response response) {
        return requestHandler.handle(request, response);
    }

    public void start() {
        server.start(new WebApplicationServlet(this));
    }
}
