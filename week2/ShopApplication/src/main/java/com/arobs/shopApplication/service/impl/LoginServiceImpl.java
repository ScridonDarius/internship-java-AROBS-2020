package com.arobs.shopApplication.service.impl;

import com.arobs.shopApplication.model.User;
import com.arobs.shopApplication.repository.UserList;
import com.arobs.shopApplication.service.LoginService;

import java.util.List;

public class LoginServiceImpl implements LoginService {

    public static LoginServiceImpl instance = new LoginServiceImpl();
    private  List<User> users = UserList.instance.createAccount();

    @Override
    public boolean findUser(final String userName, final String password) {

        User user = users.stream().filter(userCheck -> (userCheck.getUserName().equals(userName) && userCheck.getPassword().equals(password))).findFirst().orElse(null);

        if (user != null) {
            return true;
        }
        return false;
    }



}

