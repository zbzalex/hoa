package com.web3horizen.hoa.example.app;

import com.web3horizen.hoa.framework.annotation.Module;
import com.web3horizen.hoa.example.modules.home.HomeModule;
import com.web3horizen.hoa.example.modules.user.UserModule;

@Module(modules = {
        HomeModule.class,
        UserModule.class
})
public class AppModule {
}
