/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.group03.loveit.controllers.authentication;

import com.group03.loveit.models.user.UserDAO;
import com.group03.loveit.models.user.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Login Controller
 *
 * - not done unsimplified path
 *
 * - not done attached to the entire session
 *
 * @author duyvu
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/authentication/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UserDAO userDAO = new UserDAO();
            // Change this back once the login method is implemented
            //UserDTO user = userDAO.login(email, password);
            UserDTO user = userDAO.getUserById(2);

            if (user != null) {
                request.getSession(true).setAttribute("USER-SESSION", user);
                response.sendRedirect("people-zone");
            } else {
                request.setAttribute("error", "Wrong email or password. Please try again");
                request.getRequestDispatcher("/views/authentication/login.jsp").forward(request, response);
            }

        } catch (IOException | ServletException e) {
            log("Error on login: " + e.getMessage());
        }
    }
}
