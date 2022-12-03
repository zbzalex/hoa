package com.web3horizen.hoa.example.modules.user;

import com.web3horizen.hoa.example.modules.user.controllers.UserController;
import com.web3horizen.hoa.framework.annotation.Module;

@Module(controllers = {
        UserController.class
})
public class UserModule {
}
