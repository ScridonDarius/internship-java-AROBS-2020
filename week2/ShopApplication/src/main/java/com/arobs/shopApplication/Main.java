package com.arobs.shopApplication;

import com.arobs.shopApplication.model.Product;
import com.arobs.shopApplication.model.User;
import com.arobs.shopApplication.service.impl.ProductServiceImpl;
import com.arobs.shopApplication.service.impl.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        ProductServiceImpl productService = new ProductServiceImpl();

        User user = new User("Ben", "Darius");
        Product product = new Product("Banana",9.99,10);


    }
}
