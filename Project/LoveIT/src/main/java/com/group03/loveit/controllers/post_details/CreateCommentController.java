package com.group03.loveit.controllers.post_details;

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
/**
 *
 * @author Nhat
 */
public class CreateCommentController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String action = request.getParameter("action");
            long postId;
            if (action != null) {
                switch (action) {
                    case "create_comment":
                        postId = Long.parseLong(request.getParameter("post_id"));
                        createComment(request, postId);
                        response.sendRedirect(request.getContextPath() + "/post-details?post_id=" + postId);
                        break;
                    case "create_reply":
                        postId = Long.parseLong(request.getParameter("post_id"));
                        createReply(request);
                        response.sendRedirect(request.getContextPath() + "/post-details?post_id=" + postId);
                        break;
                }
            }
        } catch (NumberFormatException e) {
            log("Error parsing post id: " + e.getMessage());
        } catch (Exception e) {
            log("Unexpected error: " + e.getMessage());
        }
    }

    private void createComment(HttpServletRequest request, long postId) {
        String content = request.getParameter("content");

        PostDAO postDAO = new PostDAO();
        PostDTO post = postDAO.getPostById(postId).join();

        CommentDAO commentDAO = new CommentDAO();
        CommentDTO comment = new CommentDTO(post, getCurrentUser(), content, LocalDateTime.now(), "Active", null);
        commentDAO.insertComment(comment).join();
    }

    private void createReply(HttpServletRequest request) throws NumberFormatException {
        String content = request.getParameter("reply_content");
        long parentCmtId = Long.parseLong(request.getParameter("parent_cmt_id"));

        CommentDAO commentDAO = new CommentDAO();
        CommentDTO parentComment = commentDAO.getCommentById(parentCmtId).join();

        CommentDTO reply = new CommentDTO(parentComment.getPost(), getCurrentUser(), content, LocalDateTime.now(), "Active", parentComment);
        commentDAO.insertComment(reply).join();
    }

    // Temporary method to get the current user
    public static UserDTO getCurrentUser() {
        UserDAO userDAO = new UserDAO();
        return userDAO.getUserById(2);
    }
}
