package com.group03.loveit.controllers;

import com.group03.loveit.models.comment.CommentDAO;
import com.group03.loveit.models.comment.CommentDTO;
import com.group03.loveit.models.post.PostDAO;
import com.group03.loveit.models.post.PostDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PostDetailsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long post_id = Long.parseLong(request.getParameter("post_id"));
            PostDAO postDAO = new PostDAO();
            PostDTO post = postDAO.getPostById(post_id).get();

            CommentDAO commentDAO = new CommentDAO();
            List<CommentDTO> comments = commentDAO.getCommentsByPost(post_id).get();

            request.setAttribute("post", post);
            request.setAttribute("comments", comments);
            request.getRequestDispatcher("/views/post-details/post-details.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            log("Error parsing id: " + e.getMessage());
        } catch (InterruptedException | ExecutionException e) {
            log("Error getting post: " + e.getMessage());
        } catch (Exception e) {
            log("Error: " + e.getMessage());
        }
    }
}
