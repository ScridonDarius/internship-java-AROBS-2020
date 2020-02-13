package com.arobs.shopApplication.repository;

import com.arobs.shopApplication.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {

    List<Product> getAll() throws SQLException;

    Product findByProductName(String productName) throws SQLException;

    int insertProduct(Product product) throws SQLException;

    void updateProduct(int productId, Product product) throws SQLException;

    void deleteProduct(int productId) throws SQLException;
}
