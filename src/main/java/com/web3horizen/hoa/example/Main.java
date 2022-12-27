package com.web3horizen.hoa.example;

import com.web3horizen.hoa.example.app.AppModule;
import com.web3horizen.hoa.framework.Application;
import com.web3horizen.hoa.framework.WebApplication;

public class Main {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws Exception {
        Application app = new WebApplication();
        app.init(AppModule.class);
        app.start();
    }
}
