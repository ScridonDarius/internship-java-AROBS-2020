package com.arobs.shopApplication;

import com.arobs.shopApplication.model.User;
import com.arobs.shopApplication.service.impl.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        User user = new User("Ben", "Darius");

        try {
            System.out.println(userService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
