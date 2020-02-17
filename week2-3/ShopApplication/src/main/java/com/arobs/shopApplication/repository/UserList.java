package com.arobs.shopApplication.repository;

import com.arobs.shopApplication.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserList {

    public static UserList instance = new UserList();

    private List<User> users = new ArrayList<>();

    public List<User> createAccount(){
        users.add(new User("Darius", "darius"));
        users.add(new User("Ben", "ben"));
        users.add(new User("Admin", "admin"));

        return users;
    }
}
