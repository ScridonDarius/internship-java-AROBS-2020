package com.arobs.shopApplication.repository.impl;

import com.arobs.shopApplication.config.database.DataSource;
import com.arobs.shopApplication.dto.UserDTO;
import com.arobs.shopApplication.model.User;
import com.arobs.shopApplication.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public User findByUserName(String userName) throws SQLException {
        User user = null;

        connection = DataSource.getConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE user_name = ?");

        preparedStatement.setString(1, userName);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);
            user = new User(name, password);
        }
        connection.close();
        return user;
    }

    @Override
    public int insertUser(User user) {
        int userId = -1;

        try {
            connection = DataSource.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO user (user_name,user_password)" + " VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                userId = resultSet.getInt(1);
            }

            user.setId(userId);
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userId;
    }

    @Override
    public void updateUser(int userId, User user) throws SQLException {

        try {
            connection = DataSource.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE user SET user_name = ?, user_password = ? WHERE user_id = ?", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, userId);

            preparedStatement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int userId) throws SQLException {
        connection = DataSource.getConnection();
        preparedStatement = connection.prepareStatement("DELETE FROM user WHERE user_id = " + userId + " ", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();

        connection.close();
    }

    @Override
    public List<UserDTO> findAll() throws SQLException {
        connection = DataSource.getConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM user", Statement.RETURN_GENERATED_KEYS);

        List<UserDTO> users = new ArrayList<>();

        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            UserDTO user = new UserDTO(id, name);
            users.add(user);
        }

        connection.close();
        return users;
    }
}
