/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.group03.loveit.controllers.profile;

import com.group03.loveit.models.user.UserDAO;
import com.group03.loveit.models.user.UserDTO;
import com.group03.loveit.utilities.ConstantUtils;
import com.group03.loveit.utilities.CryptoUtils;
import com.group03.loveit.utilities.DataProcessingUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author duyvu
 */
public class ProfileController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Checking action
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");

        // Neu user login -> bam vao profile, vao trang profile, hien ra profile cua minh trong session => tu o popup => /profile?action=myProfile
        // Neu user login -> trong trang profile, co the chinh sua thong tin => /profile?action=update 
        // Neu user login -> bam vao other profile, vao trang profile, hien ra profile nguoi khac => /profile?action=otherProfile?id=1
        // Neu user chua login -> bam vao other profile, vao trang profile, hien ra profile ngguoi khac => /profile?action=otherProfile?id=1
        // Neu user chua login -> khong the nao bam vao trang myProfile => vao trang people-zone (DONE)
        if (action == null) {
            response.sendRedirect("people-zone");
        } else if ("myProfile".equals(action) && session.getAttribute(ConstantUtils.SESSION_USER) != null) {
            request.setAttribute("action", "update");
            request.getRequestDispatcher("/views/user/profile.jsp").forward(request, response);
        } else if ("update".equals(action) && session.getAttribute(ConstantUtils.SESSION_USER) != null) {

            String oldPassword = request.getParameter("newPassword");
            String newPassword = request.getParameter("newPassword");
            String retypePassword = request.getParameter("retypePassword");

            // Hash Map contains error
            Map<String, String> errorMessages = new HashMap<>();

            // Fixing Old Password
            if (!DataProcessingUtils.isPasswordValid(newPassword)) {
                errorMessages.put("errorPassword", "Please enter password in the right format");
            }

            if (!DataProcessingUtils.isRetypedPasswordValid(newPassword, retypePassword)) {
                errorMessages.put("errorRetypePassword", "Please enter retype password in the right format");
            }

            UserDTO user = (UserDTO) session.getAttribute(ConstantUtils.SESSION_USER);

        } else if ("otherProfile".equals(action)) {
            UserDAO userDAO = new UserDAO();
            long userId = Long.parseLong(request.getParameter("userId"));
            UserDTO otherUser = userDAO.getUserById(userId);
            request.setAttribute("user", otherUser);
            request.getRequestDispatcher("/views/user/profile.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
