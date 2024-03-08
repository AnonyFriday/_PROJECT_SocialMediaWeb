package com.group03.loveit.controllers.people_zone;

import com.group03.loveit.models.comment.CommentDAO;
import com.group03.loveit.models.comment.CommentDTO;
import com.group03.loveit.models.post.PostDAO;
import com.group03.loveit.models.post.PostDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class PeopleZoneController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher dispatcher;
        if (action != null) {
            switch (action) {
                case "post_details":
                    String postId = request.getParameter("post_id");
                    if (postId != null) {
                        dispatcher = request.getRequestDispatcher("/post-details?post_id=" + postId);
                        dispatcher.forward(request, response);
                    }
                    break;
                default:
                    break;
            }
        } else {
            processPostList(request, response);
            dispatcher = request.getRequestDispatcher("/views/people-zone/people-zone.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "create_post":
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/create-post");
                    dispatcher.forward(request, response);
                    break;
                default:
                    break;
            }
        }
    }

    private void processPostList(HttpServletRequest request, HttpServletResponse response) {
        PostDAO postDAO = new PostDAO();
        CommentDAO commentDAO = new CommentDAO();
        List<PostDTO> posts = null;
        try {
            posts = postDAO.getAllPosts().get();
            for (PostDTO post : posts) {
                CommentDTO topComment = commentDAO.getTopCommentByPost(post.getId()).get();
                post.setTopComment(topComment);
            }
        } catch (InterruptedException | ExecutionException e) {
            log("Error getting posts or top comments: " + e.getMessage());
        }
        request.setAttribute("posts", posts);
    }
}
