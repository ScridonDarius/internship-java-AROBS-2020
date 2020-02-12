package com.arobs.shopApplication.repository;

import com.arobs.shopApplication.model.Stock;
import com.arobs.shopApplication.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductList {

    public static ProductList instance = new ProductList();
    private List<Stock> stocks = new ArrayList<>();

    public ProductList() {
        stocks.add(new Stock(new Product("1", "cake", 12), 30));
        stocks.add(new Stock(new Product("2", "candy", 15), 50));
        stocks.add(new Stock(new Product("3", "pops", 20), 100));
    }

    public List<Stock> getStocks() {
        return stocks;
    }
}
