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
}
