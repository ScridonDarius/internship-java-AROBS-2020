package com.arobs.shopApplication.repository;

import com.arobs.shopApplication.dto.UserDTO;
import com.arobs.shopApplication.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {

    User findByUserName(String userName) throws SQLException;

    int insertUser(User user) throws SQLException;

    void updateUser(int userId, User user) throws SQLException;

    void delete(int userId) throws SQLException;

    List<UserDTO> findAll() throws SQLException;
}
