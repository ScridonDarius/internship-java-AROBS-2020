package com.arobs.shopApplication.service;

import com.arobs.shopApplication.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    int insertProduct(Product product) throws SQLException;

    Product findByProductName(String productName) throws SQLException;

    List<Product> getAll() throws SQLException;
}
