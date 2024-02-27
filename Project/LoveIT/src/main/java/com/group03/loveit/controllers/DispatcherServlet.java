/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.group03.loveit.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {

    // ..... adding more controllers when developing further
    private final String CONTROLLER_LOGIN = "LoginController";
    private final String CONTROLLER_REGISTER = "RegisterController";
    private final String CONTROLLER_WELCOME = "WelcomeController";

    // ..... adding more pages when developing further
    private final String PAGE_INDEX = "index.jsp";
    private final String PAGE_WELCOME = "views/welcome.jsp";
    private final String PAGE_HOME = "views/home.jsp";
    private final String PAGE_LOGIN = "views/login.jsp";
    private final String PAGE_REGISTER = "views/register.jsp";
    private final String PAGE_FORGOT_PASSSWORD = "views/forgot_password.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Default View
        String view = "/";

        // e.g. http://example.com/contextPath/servletPath/pathInfo
        // will return /servletPath
        String path = request.getServletPath();

        try {
            if ("/".equals(path)) {
                view += PAGE_INDEX;
            } else if ("/login".equals(path)) {
                view += PAGE_LOGIN;
            } else if ("/register".equals(path)) {
                view += PAGE_REGISTER;
            } else if ("/home".equals(path)) {
                view += PAGE_HOME;
            } else if ("/welcome".equals(path)) {
                view += PAGE_WELCOME;
            }
        } finally {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(view);
            requestDispatcher.forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
