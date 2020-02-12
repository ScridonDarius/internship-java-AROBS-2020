package com.arobs.shopApplication.service;

import com.arobs.shopApplication.model.Stock;

import java.util.List;

public interface ItemService {

    public List<Stock> getAll();

    public Stock findById(String id);
}
