package com.arobs.shopApplication.controller;

import com.arobs.shopApplication.model.Item;
import com.arobs.shopApplication.model.Product;
import com.arobs.shopApplication.repository.ProductList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        List<Item> cartItems = (List<Item>) request.getSession().getAttribute("cart");
        Item searchedItem = cartItems.stream()
                .filter(item -> item.getProduct().getId().equals(request.getParameter("id")))
                .findFirst().get();

        cartItems.remove(searchedItem);
        request.getSession().setAttribute("cart", cartItems);
        response.sendRedirect("cart.jsp");
    }

    //Quantity to much check
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("productId");
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));

        List<Item> cartItems = (List<Item>) request.getSession().getAttribute("cart");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        Item searchedItem = cartItems.stream()
                .filter(item -> item.getProduct().getId().equals(id))
                .findFirst().orElse(null);

        if (searchedItem == null) {
            searchedItem = ProductList.instance.getItems().stream()
                    .filter(item -> item.getProduct().getId().equals(id))
                    .findFirst().orElse(null);

            if (quantity > searchedItem.getQuantity()) {
                request.setAttribute("qtyErrorMessage", "The quantity is to high");
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
                return;
            }

            searchedItem.setQuantity(quantity);
            cartItems.add(searchedItem);
        } else {
            if (quantity > searchedItem.getQuantity() + quantity) {
                request.setAttribute("qtyErrorMessage", "The quantity is to high");
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
                return;
            }
            searchedItem.setQuantity(searchedItem.getQuantity() + quantity);
        }

        request.getSession().setAttribute("cart", cartItems);
        response.sendRedirect("cart");
    }

}