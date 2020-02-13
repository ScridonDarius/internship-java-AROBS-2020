package com.arobs.shopApplication.repository.impl;

import com.arobs.shopApplication.config.database.DataSource;
import com.arobs.shopApplication.model.Product;
import com.arobs.shopApplication.repository.ProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    public static ProductRepositoryImpl instance = new ProductRepositoryImpl();

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public List<Product> getAll() throws SQLException {

        connection = DataSource.getConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM product", Statement.RETURN_GENERATED_KEYS);

        List<Product> products = new ArrayList<>();

        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            Double price = resultSet.getDouble(3);
            int quantity = resultSet.getInt(4);
            Product product = new Product(id, name, price, quantity);
            products.add(product);
        }
        connection.close();
        return products;
    }

    @Override
    public Product findByProductName(String productName) throws SQLException {
        Product product = null;

        connection = DataSource.getConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE product_name = ?");
        preparedStatement.setString(1, productName);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            Double price = resultSet.getDouble(3);
            int quantity = resultSet.getInt(4);
            product = new Product(id, name, price, quantity);
        }

        connection.close();
        return product;
    }

    @Override
    public int insertProduct(Product product) throws SQLException {
        int productId = -1;

        connection = DataSource.getConnection();
        preparedStatement = connection.prepareStatement("INSERT INTO product(product_name,product_price,product_quantity)" + " VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setInt(3, product.getQuantity());

        preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();

        if (resultSet.next()) {
            productId = resultSet.getInt(1);
        }

        product.setId(productId);
        connection.close();
        return productId;
    }

    @Override
    public void updateProduct(int productId, Product product) throws SQLException {

    }

    @Override
    public void deleteProduct(int productId) throws SQLException {

    }
}
