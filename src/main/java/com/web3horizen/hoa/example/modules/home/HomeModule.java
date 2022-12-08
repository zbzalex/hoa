package com.web3horizen.hoa.example.modules.home;

import com.web3horizen.hoa.framework.annotations.Module;
import com.web3horizen.hoa.example.modules.home.controllers.HomeController;

@Module(controllers = {
        HomeController.class
})
public class HomeModule {
}
