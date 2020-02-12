package com.arobs.shopApplication.repository;

import com.arobs.shopApplication.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {

    User findByUserName(String userName) throws SQLException;

    int insertUser(User user) throws SQLException;

    void updateUser(User user) throws SQLException;

    void delete(User user) throws SQLException;

    List<User> findAll() throws SQLException;
}
