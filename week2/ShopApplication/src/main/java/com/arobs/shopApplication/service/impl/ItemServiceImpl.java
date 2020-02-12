package com.arobs.shopApplication.service.impl;

import com.arobs.shopApplication.model.Stock;
import com.arobs.shopApplication.repository.ProductList;
import com.arobs.shopApplication.service.ItemService;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    public static ItemServiceImpl instance = new ItemServiceImpl();

    @Override
    public List<Stock> getAll() {
        return ProductList.instance.getStocks();
    }

    @Override
    public Stock findById(String id) {

        List<Stock> items = ProductList.instance.getStocks();
        Stock stock = items.stream().filter(itemCheck ->(itemCheck.getProduct().getId().equals(id))).findFirst().orElse(null);

        Stock element = items.get(Integer.parseInt(id)-1);
        if(stock !=null){
           return element;
        }
        return null;
    }
}
