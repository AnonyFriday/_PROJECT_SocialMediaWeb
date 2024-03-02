package com.group03.loveit.controllers;

import com.group03.loveit.models.post.PostDAO;
import com.group03.loveit.models.post.PostDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PeopleZoneController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processPostList(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/people-zone/people-zone.jsp");
        dispatcher.forward(request, response);
    }

    private void processPostList(HttpServletRequest request, HttpServletResponse response) {
        PostDAO postDAO = new PostDAO();
        List<PostDTO> posts = postDAO.getAllPosts().join();
        request.setAttribute("posts", posts);
    }
}
