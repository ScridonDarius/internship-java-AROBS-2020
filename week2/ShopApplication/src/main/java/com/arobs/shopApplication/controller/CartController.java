package com.arobs.shopApplication.controller;

import com.arobs.shopApplication.model.Stock;
import com.arobs.shopApplication.repository.ProductList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//de ajustat

@WebServlet(name = "cart", urlPatterns = "/cart")
public class CartController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            doGet_DisplayCart(request, response);
        } else {
            } if (action.equalsIgnoreCase("remove")) {
                doDelete(request, response);
            }
        }


    protected void doGet_DisplayCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Stock> cartStocks = (List<Stock>) request.getSession().getAttribute("cart");
        Stock searchedStock = cartStocks.stream()
                .filter(item -> item.getProduct().getId().equals(request.getParameter("id")))
                .findFirst().get();

        cartStocks.remove(searchedStock);
        request.getSession().setAttribute("cart", cartStocks);
        response.sendRedirect("cart.jsp");
    }

    //Quantity to much check
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("productId");
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));

        List<Stock> cartStocks = (List<Stock>) request.getSession().getAttribute("cart");
        if (cartStocks == null) {
            cartStocks = new ArrayList<>();
        }

        Stock searchedStock = cartStocks.stream()
                .filter(item -> item.getProduct().getId().equals(id))
                .findFirst().orElse(null);

        if (searchedStock == null) {
            searchedStock = ProductList.instance.getStocks().stream()
                    .filter(item -> item.getProduct().getId().equals(id))
                    .findFirst().orElse(null);

            if (quantity > searchedStock.getQuantity()) {
                request.setAttribute("qtyErrorMessage", "The quantity is to high");
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
                return;
            }

            searchedStock.setQuantity(quantity);
            cartStocks.add(searchedStock);
        } else {
            if (quantity > searchedStock.getQuantity() + quantity) {
                request.setAttribute("qtyErrorMessage", "The quantity is to high");
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
                return;
            }
            searchedStock.setQuantity(searchedStock.getQuantity() + quantity);
        }

        request.getSession().setAttribute("cart", cartStocks);
        response.sendRedirect("cart");
    }

}