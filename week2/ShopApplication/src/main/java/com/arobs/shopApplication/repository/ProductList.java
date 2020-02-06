package com.arobs.shopApplication.repository;

import com.arobs.shopApplication.model.Item;
import com.arobs.shopApplication.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductList {

    public static ProductList instance = new ProductList();
    private List<Item> items = new ArrayList<>();


    public List<Item>createProducts(){
        items.add(new Item(new Product("1","cake",12), 30));
        items.add(new Item(new Product("2","candy",15), 50));
        items.add(new Item(new Product("3","pops",20), 100));

        return items;
    }
}
