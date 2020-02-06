package com.arobs.shopApplication.controller;

import com.arobs.shopApplication.repository.UserList;
import com.arobs.shopApplication.service.impl.LoginServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/loginPage")
public class LoginController extends HttpServlet {

    LoginServiceImpl loginService;
    UserList userList;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        boolean checkValidation = loginService.instance.findUser(userName, password);


        if (checkValidation) {

            session.setAttribute("login", "true");
            response.sendRedirect("products.jsp");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

    }
}

