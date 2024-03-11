/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.group03.loveit.controllers.authentication;

import com.group03.loveit.models.account.AccountDAO;
import com.group03.loveit.models.account.AccountDTO;
import com.group03.loveit.models.account.EAccountRole;
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

        AccountDAO accountDAO = new AccountDAO();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String errorMsg = null;

        try {
            AccountDTO user = accountDAO.login(email, password);

            // Check if having user then added to session else showing error
            if (user != null) {
                request.getSession(true).setAttribute("USER-SESSION", user);

                // Checking account status
                switch (user.getStatus()) {
                    case DISABLE: {
                        errorMsg = "Your account has been disabled. Please contact the phone 0909189999";
                        break;
                    }
                    case ACTIVE: {
                        redirectToRolePage(user.getRole(), response);
                        break;
                    }
                }

            } else {
                errorMsg = "Your email or password is not correct. Please check again.";
            }

            // If having error, sending error with message
            // then come back to login
            if (errorMsg != null) {
                request.setAttribute("error", errorMsg);
                request.getRequestDispatcher("/views/authentication/login.jsp").forward(request, response);
            }

        } catch (IOException | ServletException e) {
            log("Error on login: " + e.getMessage());
        }
    }

    /**
     * Sending to page based on account role
     *
     * @param role
     * @param response
     * @throws IOException
     */
    private void redirectToRolePage(EAccountRole role, HttpServletResponse response) throws IOException {
        switch (role) {
            case ADMIN: {
                response.sendRedirect("admin");
                break;
            }
            case USER: {
                response.sendRedirect("people-zone");
                break;
            }
        }
    }
}
