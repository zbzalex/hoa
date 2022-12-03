package com.web3horizen.web.app;

import com.web3horizen.web.framework.annotation.Module;
import com.web3horizen.web.modules.home.HomeModule;
import com.web3horizen.web.modules.user.UserModule;

@Module(modules = {
        HomeModule.class,
        UserModule.class
})
public class AppModule {
}
