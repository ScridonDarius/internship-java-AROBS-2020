package com.arobs.shopApplication;

import com.arobs.shopApplication.model.User;
import com.arobs.shopApplication.service.impl.UserServiceImpl;

public class Main {

    public static void main(String[] args) {


        UserServiceImpl userService = new UserServiceImpl();
        User user = new User("Darius","Darius");
        try {
           // userService.insertUser(user);
          // User darius = userService.findByUserName("Darius");
           // System.out.println(darius);
            System.out.println( userService.authenticate("Darius","Darius"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
