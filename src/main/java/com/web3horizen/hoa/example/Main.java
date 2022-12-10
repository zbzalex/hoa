package com.web3horizen.hoa.example;

import com.web3horizen.hoa.example.app.AppModule;
import com.web3horizen.hoa.framework.Application;

public class Main {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws Exception {
        // create application
        Application app = new App();
        // set application main module class
        app.setMainModuleClass(AppModule.class);
        // initialize application
        app.run();
    }
}
