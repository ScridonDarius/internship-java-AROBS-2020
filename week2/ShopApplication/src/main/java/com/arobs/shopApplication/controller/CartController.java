package com.arobs.shopApplication.controller;

import com.arobs.shopApplication.model.CartLine;
import com.arobs.shopApplication.model.Product;
import com.arobs.shopApplication.repository.impl.ProductRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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

//        List<CartLine> cartStocks = (List<CartLine>) request.getSession().getAttribute("cart");
//        CartLine searchedStock = cartStocks.stream()
//                .filter(item -> item.getProduct().getId().equals(request.getParameter("id")))
//                .findFirst().get();
//
//        cartStocks.remove(searchedStock);
//        request.getSession().setAttribute("cart", cartStocks);
//        response.sendRedirect("cart.jsp");
    }

    //Quantity to much check

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("productId");
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));

        List<Product> cartStocks = (List<Product>) request.getSession().getAttribute("cart");
        if (cartStocks == null) {
            cartStocks = new ArrayList<>();
        }

       Product searchedStock = cartStocks.stream()
                .filter(item -> Integer.toString(item.getId()).equals(id))
                .findFirst().orElse(null);

//
         try {
            if (searchedStock == null) {
                searchedStock = ProductRepositoryImpl.instance.getAll().stream()
                        .filter(item -> Integer.toString(item.getId()).equals(id))
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


            }catch(Exception e){

            }

            request.getSession().setAttribute("cart", cartStocks);
            response.sendRedirect("cart");
        }
}