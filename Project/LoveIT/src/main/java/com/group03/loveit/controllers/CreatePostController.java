package com.group03.loveit.controllers;

import com.group03.loveit.models.post.PostDAO;
import com.group03.loveit.models.post.PostDTO;
import com.group03.loveit.models.account.AccountDTO;
import com.group03.loveit.models.user.UserDAO;
import com.group03.loveit.models.user.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class CreatePostController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = request.getParameter("content");
        String imageUrl = request.getParameter("imageUrl");

        UserDAO userDAO = new UserDAO();
        UserDTO user = userDAO.getUserById(2);

        PostDTO post = new PostDTO(user, content, LocalDateTime.now(), 0, 0, "Active", imageUrl);
        PostDAO postDAO = new PostDAO();
        postDAO.insertPost(post);

        response.sendRedirect(request.getContextPath() + "/people-zone");
    }
}