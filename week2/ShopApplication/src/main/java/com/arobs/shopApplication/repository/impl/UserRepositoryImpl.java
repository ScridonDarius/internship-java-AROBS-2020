package com.arobs.shopApplication.repository.impl;

import com.arobs.shopApplication.config.database.ConnectionManager;
import com.arobs.shopApplication.model.User;
import com.arobs.shopApplication.repository.UserRepository;

import java.sql.*;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {


    public static final String insertStatement = "INSERT INTO user (user_name,user_password)" + " VALUES(?,?)";

    @Override
    public User findByUserName(String userName) throws SQLException {
        Connection connection = ConnectionManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE user_name = ?", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, userName);

        User user = null;
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);
            user = new User(name, password);
        }

        ConnectionManager.closeConnection(connection);
        ConnectionManager.closeStatement(preparedStatement);

        return user;
    }

    @Override
    public int insertUser(User user) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = null;

        int user_id = -1;

        preparedStatement = connection.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            user_id = resultSet.getInt(1);
        }

        user.setId(user_id);

        ConnectionManager.closeConnection(connection);
        ConnectionManager.closeStatement(preparedStatement);
        ConnectionManager.closeResultSet(resultSet);

        return user_id;
    }

    @Override
    public void updateUser(User user) throws SQLException {

    }

    @Override
    public void delete(User user) throws SQLException {

    }

    @Override
    public List<User> findAll() throws SQLException {
        return null;
    }
}
