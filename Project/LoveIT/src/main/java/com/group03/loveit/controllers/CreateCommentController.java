package com.group03.loveit.controllers;

import com.group03.loveit.models.comment.CommentDAO;
import com.group03.loveit.models.comment.CommentDTO;
import com.group03.loveit.models.post.PostDAO;
import com.group03.loveit.models.post.PostDTO;
import com.group03.loveit.models.user.UserDAO;
import com.group03.loveit.models.user.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

public class CreateCommentController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long post_id = Long.parseLong(request.getParameter("post_id"));
            String content = request.getParameter("content");

            PostDAO postDAO = new PostDAO();
            PostDTO post = postDAO.getPostById(post_id).get();

            UserDAO userDAO = new UserDAO();
            UserDTO user = userDAO.getUserById(2);

            CommentDAO commentDAO = new CommentDAO();
            CommentDTO comment = new CommentDTO(post, user, content, LocalDateTime.now(), "Active", null);
            commentDAO.insertComment(comment);

            response.sendRedirect(request.getContextPath() + "/post-details?post_id=" + post_id);
        } catch (ExecutionException | InterruptedException | NumberFormatException e) {
            log("Error creating comment: " + e.getMessage());
        }
    }
}
