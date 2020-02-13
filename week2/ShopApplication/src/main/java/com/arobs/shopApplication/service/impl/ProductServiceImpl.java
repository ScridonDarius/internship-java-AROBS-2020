package com.arobs.shopApplication.service.impl;

import com.arobs.shopApplication.model.Product;
import com.arobs.shopApplication.repository.ProductRepository;
import com.arobs.shopApplication.repository.impl.ProductRepositoryImpl;
import com.arobs.shopApplication.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    public static ProductServiceImpl instance = new ProductServiceImpl();

    public ProductServiceImpl() {
        this.productRepository = new ProductRepositoryImpl();
    }

    @Override
    public int insertProduct(Product product) throws SQLException {
        return this.productRepository.insertProduct(product);
    }

    @Override
    public Product findByProductName(String productName) throws SQLException {
        return this.productRepository.findByProductName(productName);
    }

    @Override
    public List<Product> getAll() throws SQLException {
        return this.productRepository.getAll();
    }
}
