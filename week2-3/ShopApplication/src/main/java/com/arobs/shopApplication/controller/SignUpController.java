package com.arobs.shopApplication.controller;

import com.arobs.shopApplication.dto.UserSignUpDTO;
import com.arobs.shopApplication.model.User;
import com.arobs.shopApplication.service.UserService;
import com.arobs.shopApplication.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "signUp", urlPatterns = "/signUp")
public class SignUpController extends HttpServlet {
    private UserService userService;

    public SignUpController() {
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/signUp.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String confirmationPassword = request.getParameter("confirmationPassword");

        try{
        if(password.equals(confirmationPassword)) {
            User user = null;
            user.setPassword(password);
            user.setUserName(userName);
            userService.insertUser(user);
        }}catch (Exception e){
            e.printStackTrace();
        }

        if (password.equals(confirmationPassword)) {
            request.getSession().setAttribute("currentSession", userName);
            response.sendRedirect("login");
        } else {
            request.getRequestDispatcher("invalidSignUp.jsp").forward(request, response);
        }
    }
}
