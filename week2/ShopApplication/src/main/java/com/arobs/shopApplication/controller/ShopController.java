package com.arobs.shopApplication.controller;

import com.arobs.shopApplication.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "shop", urlPatterns = "/products")
public class ShopController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ShopController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession();
        request.setAttribute("items", ItemServiceImpl.instance.getAll());
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }
}
