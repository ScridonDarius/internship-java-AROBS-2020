package com.arobs.shopApplication.service.impl;

import com.arobs.shopApplication.model.Item;
import com.arobs.shopApplication.repository.ProductList;
import com.arobs.shopApplication.service.ItemService;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    public static ItemServiceImpl instance = new ItemServiceImpl();

    @Override
    public List<Item> getAll() {
        return ProductList.instance.createProducts();
    }

    @Override
    public Item findById(String id) {

        List<Item> items = ProductList.instance.createProducts();
        Item item = items.stream().filter(itemCheck ->(itemCheck.getProduct().getId().equals(id))).findFirst().orElse(null);

        Item element = items.get(Integer.parseInt(id)-1);
        if(item !=null){
           return element;
        }
        return null;
    }
}
