package com.arobs.shopApplication.controller;

import com.arobs.shopApplication.service.UserService;
import com.arobs.shopApplication.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private UserService userService;

    public LoginController() {
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        if (userService.authenticate(userName, password)) {
            request.getSession().setAttribute("currentSession", userName);
            response.sendRedirect("products");
        } else {
            request.getRequestDispatcher("invalidLogin.jsp").forward(request, response);
        }
    }
}

