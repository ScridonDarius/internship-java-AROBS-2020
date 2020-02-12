package com.arobs.shopApplication.config.database;

import java.sql.*;

public class ConnectionManager {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/shopapplication";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "1234";

    // Singleton pattern
    private static ConnectionManager singleInstance;

    static {
        try {
            singleInstance = new ConnectionManager();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ConnectionManager() throws ClassNotFoundException{
        try{
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL,USER,PASS);
        return connection;
    }

    public static Connection getConnection() throws SQLException {
        return singleInstance.createConnection();
    }

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public static void closeStatement(Statement statement) throws SQLException {
        statement.close();
    }

    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }
}