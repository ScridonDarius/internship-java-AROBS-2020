package com.arobs.shopApplication.controller;

import com.arobs.shopApplication.model.Item;
import com.arobs.shopApplication.model.Product;

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
            if (action.equalsIgnoreCase("buy")) {
                doGet_Buy(request, response);
            } else if (action.equalsIgnoreCase("remove")) {
                doGet_Remove(request, response);
            }
        }
    }

    protected void doGet_DisplayCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    protected void doGet_Remove(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = isExisting(request.getParameter("id"), cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }

    protected void doGet_Buy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String quantityToOrder =  request.getParameter("quantity");

        // int quantity = Integer.parseInt(quantityToOrder);

        //hardodat
        Item item = new Item(new Product("1","cake",21),23);
        item.getProduct().getId();

        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {


            List<Item> cart = new ArrayList<Item>();
           cart.add(item);
            session.setAttribute("cart", cart);
        } else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExisting(request.getParameter("id"), cart);
            if (index == -1) {
                cart.add(item);
            } else {
             int   quantity = cart.get(index).getQuantity() + 1;

                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        response.sendRedirect("cart");
    }

    private int isExisting(String id, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}