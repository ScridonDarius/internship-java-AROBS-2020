package com.arobs.shopApplication.service.impl;

import com.arobs.shopApplication.dto.UserDTO;
import com.arobs.shopApplication.model.User;
import com.arobs.shopApplication.repository.UserRepository;
import com.arobs.shopApplication.repository.impl.UserRepositoryImpl;
import com.arobs.shopApplication.service.UserService;

import java.sql.SQLException;
import java.util.List;

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

    @Override
    public int insertUser(User user) throws SQLException {
        return this.userRepository.insertUser(user);
    }

    @Override
    public void updateUser(int userId, User user) throws SQLException {
        this.userRepository.updateUser(userId, user);
    }

    public void delete(int userId) throws SQLException {
        userRepository.delete(userId);
    }

    @Override
    public List<UserDTO> findAll() throws SQLException {
        return this.userRepository.findAll();
    }
}
