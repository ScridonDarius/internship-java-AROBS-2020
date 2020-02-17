package com.arobs.shopApplication.service;

import com.arobs.shopApplication.dto.UserDTO;
import com.arobs.shopApplication.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    boolean authenticate(String userName, String password);

    int insertUser(User user) throws SQLException;

    void updateUser(int userId, User user) throws SQLException;

    void delete(int userId) throws SQLException;

    List<UserDTO> findAll()throws SQLException;

}
