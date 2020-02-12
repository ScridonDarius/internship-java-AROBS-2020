package com.arobs.shopApplication.service;

import com.arobs.shopApplication.model.User;

import java.sql.SQLException;

public interface UserService {

    boolean authenticate(String userName, String password);

    int insertUser(User user) throws SQLException;
}
