package com.group03.loveit.controllers.post_details;

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
/**
 *
 * @author Nhat
 */
public class PostDetailsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            long post_id = Long.parseLong(request.getParameter("post_id"));
            PostDAO postDAO = new PostDAO();
            PostDTO post = postDAO.getPostById(post_id).join();

            CommentDAO commentDAO = new CommentDAO();
            List<CommentDTO> comments = commentDAO.getCommentsByPost(post_id).join();

            for (CommentDTO comment : comments) {
                List<CommentDTO> replies = commentDAO.getRepliesByComment(comment.getId()).join();
                comment.setReplies(replies);
            }

            request.setAttribute("post", post);
            request.setAttribute("comments", comments);
            request.getRequestDispatcher("/views/post-details/post-details.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            log("Error parsing id: " + e.getMessage());
        } catch (Exception e) {
            log("Unexpected error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String postId;
        if (action != null) {
            switch (action) {
                case "create_comment":
                    postId = request.getParameter("post_id");
                    String content = request.getParameter("content");

                    if (postId != null && content != null) {
                        request.getRequestDispatcher("/create-comment?action=create_comment&post_id = " + postId + "&content = " + content).forward(request, response);
                    }
                    break;
                case "create_reply":
                    postId = request.getParameter("post_id");
                    String parentCmtId = request.getParameter("parent_cmt_id");
                    String replyContent = request.getParameter("reply_content");

                    if (parentCmtId != null && replyContent != null) {
                        request.getRequestDispatcher("/create-comment?action=create_reply&post_id = " + postId + "&parent_cmt_id = " + parentCmtId + "&reply_content = " + replyContent).forward(request, response);
                    }
                    break;
            }
        }
    }
}
