package com.arobs.shopApplication.service.impl;

import com.arobs.shopApplication.model.User;
import com.arobs.shopApplication.repository.UserRepository;
import com.arobs.shopApplication.repository.impl.UserRepositoryImpl;
import com.arobs.shopApplication.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
    }

    public boolean authenticate(String userName, String password) {
        try {
            User user = userRepository.findByUserName(userName);
            return user.getPassword().equals(password);
        } catch (Exception e) {
            return false;
        }
    }

    public int insertUser(User user) throws SQLException {
        return this.userRepository.insertUser(user);
    }
}
