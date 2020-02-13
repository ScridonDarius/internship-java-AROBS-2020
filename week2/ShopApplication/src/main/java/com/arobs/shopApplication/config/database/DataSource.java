package com.arobs.shopApplication.config.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static String configFile = "src/main/resources/config.properties";
    private static HikariConfig hikariConfig = new HikariConfig();

    static {
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost/shopapplication");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("1234");
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    }

    private static HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

    public DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
