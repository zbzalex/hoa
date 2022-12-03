package com.web3horizen.web.app.modules.app;

import com.web3horizen.web.app.modules.app.controllers.HomeController;
import com.web3horizen.web.app.modules.user.UserModule;
import com.web3horizen.web.framework.annotation.Module;

@Module(controllers = {
        HomeController.class
}, modules = {
        UserModule.class
})
public class AppModule {
}
