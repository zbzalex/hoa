Hoa
=============

Small java web framework

# Example

### Module

create main module

```java
@Module(modules = {
        HomeModule.class,
})
public class AppModule {
}
```

... and create some module with controllers

```java
@Module(controllers = {
        HomeController.class
})
public class HomeModule {
}
```


### Controller
```java
@Controller
public class HomeController extends com.web3horizen.hoa.framework.mvc.Controller {

    public HomeController(Session session, Request req, Response res) {
        super(session, req, res);
    }

    @RequestMapping("/")
    public Result actionHello() {
        return new Html("hello!"); // return "text/html" content type
    }
}
```

### Start server

```java
public class Main {
    public static void main(String[] args) throws Exception {
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Application app = new WebApplication();
        app.setMainModuleClass(AppModule.class);
        app.run();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
}
```