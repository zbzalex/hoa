package com.web3horizen.hoa.example.modules.home;

import com.web3horizen.hoa.framework.annotation.Module;
import com.web3horizen.hoa.example.modules.home.controllers.HomeController;

@Module(controllers = {
        HomeController.class
})
public class HomeModule {
}
