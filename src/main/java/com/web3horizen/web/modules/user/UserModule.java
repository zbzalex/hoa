package com.web3horizen.web.modules.user;

import com.web3horizen.web.modules.user.controllers.UserController;
import com.web3horizen.web.framework.annotation.Module;

@Module(controllers = {
        UserController.class
})
public class UserModule {
}
