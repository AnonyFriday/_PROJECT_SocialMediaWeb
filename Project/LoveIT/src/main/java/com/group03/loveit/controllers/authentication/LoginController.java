/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.group03.loveit.controllers.authentication;

import com.group03.loveit.models.account.AccountDAO;
import com.group03.loveit.models.account.AccountDTO;
import com.group03.loveit.models.account.EAccountRole;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String isRememberMe = request.getParameter("remember-me");
        String errorMsg = null;

        // Add cookies if 
        if (isRememberMe != null) {
            Cookie cEmail = new Cookie("cEmail", email);
            Cookie cPass = new Cookie("cPass", password);
            Cookie cRememberMe = new Cookie("cRememberMe", isRememberMe);
            cEmail.setMaxAge(60 * 60 * 24);
            cPass.setMaxAge(60 * 60 * 24);
            cRememberMe.setMaxAge(60 * 60 * 24);
            response.addCookie(cEmail);
            response.addCookie(cPass);
            response.addCookie(cRememberMe);
        } else {
            deleteCookies(request);
        }

        try {
            AccountDTO account = accountDAO.login(email, password);

            // Check if having user then added to session else showing error
            if (account != null) {

                // Checking account status
                switch (account.getStatus()) {
                    case DISABLE: {
                        errorMsg = "Your account has been disabled. Please contact the phone 0909189999";
                        break;
                    }
                    case ACTIVE: {

                        // Add session to create user session
                        request.getSession(true).setAttribute("USER-SESSION", account);

                        redirectToRolePage(account.getRole(), response);
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

    /**
     * Function to add cookies
     *
     * @param account
     * @param response //
     */
//    private void setCookies(AccountDTO account, String isRememberMe, HttpServletResponse response) {
//        // Cookie to store username, not password
//        Cookie cEmail = new Cookie("cEmail", account.getEmail());
//        Cookie cPass = new Cookie("cPass", account.getPassword());
//        Cookie cRememberMe = new Cookie("cRememberMe", isRememberMe);
//        cEmail.setMaxAge(60 * 60 * 24);
//        cPass.setMaxAge(60 * 60 * 24);
//        cRememberMe.setMaxAge(60 * 60 * 24);
//        response.addCookie(cEmail);
//        response.addCookie(cPass);
//        response.addCookie(cRememberMe);
//    }
    /**
     * Function to delete cookies
     *
     * -1: delete when exit the chrome
     *
     * 0: remove the cookie
     *
     * @param request
     */
    private void deleteCookies(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(-1);
        }
    }
}
