package com.group03.loveit.models.comment;

import com.group03.loveit.models.account.EAccountRole;
import com.group03.loveit.models.account.EAccountStatus;
import com.group03.loveit.models.post.PostDTO;
import com.group03.loveit.models.user.UserDTO;
import com.group03.loveit.utilities.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * This class provides the Data Access Object (DAO) for the Comment entity.
 * It provides methods to interact with the database such as retrieving, inserting, updating, and deleting comments.
 *
 * @author Nhat
 */
public class CommentDAO implements ICommentDAO {
    // ===========================
    // == Fields
    // ===========================
    private static final String COL_ID = "Id";
    private static final String COL_POST_ID = "Post_Id";
    private static final String COL_USER_ID = "User_Id";
    private static final String COL_CONTENT = "Content";
    private static final String COL_CREATED_AT = "Created_At";
    private static final String COL_STATUS = "Status";
    private static final String COL_REPLY_ID = "Reply_Id";

    // ===========================
    // == Override Methods
    // ===========================

    /**
     * Retrieves a comment by its ID from the database.
     *
     * @param id The ID of the comment to retrieve.
     * @return The CommentDTO object representing the comment, or null if no comment with the given ID was found.
     */
    @Override
    public CompletableFuture<CommentDTO> getCommentById(long id) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                String query = "SELECT c.*, p.*, u.* FROM Comment c " +
                               "JOIN Post p ON c.Post_Id = p.Id " +
                               "JOIN User u ON c.User_Id = u.Id " +
                               "WHERE c." + COL_ID + " = ?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setLong(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            UserDTO user = new UserDTO(
                                rs.getLong("u.Id"),
                                rs.getString("u.Email"),
                                rs.getString("u.Fullname"),
                                rs.getString("u.Image_Url"),
                                EAccountStatus.valueOf(rs.getString("u.Status")),
                                EAccountRole.valueOf(rs.getString("u.Role")),
                                rs.getByte("u.Age"),
                                rs.getLong("u.Gender_Id"),
                                rs.getLong("u.Preference_Id"),
                                rs.getString("u.Nickname")
                            );
                            PostDTO post = new PostDTO(
                                    rs.getLong("p.Id"),
                                    user,
                                    rs.getString("p.Content"),
                                    rs.getTimestamp("p.Created_At").toLocalDateTime(),
                                    rs.getInt("p.Hearts_Total"),
                                    rs.getInt("p.Comment_Total"),
                                    rs.getString("p.Status"),
                                    rs.getString("p.Image_Url")
                            );
                            CommentDTO reply = getCommentById(rs.getLong(COL_REPLY_ID)).join();
                            return new CommentDTO(
                                rs.getLong("c." + COL_ID),
                                post,
                                user,
                                rs.getString("c." + COL_CONTENT),
                                rs.getTimestamp("c." + COL_CREATED_AT).toLocalDateTime(),
                                rs.getString("c." + COL_STATUS),
                                reply
                            );
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Cannot get comment by id: " + ex.getMessage());
            }
            return null;
        });
    }

    /**
     * Retrieves all comments associated with a post.
     * 
     * @param postId The ID of the post to retrieve comments for.
     * @return A list of CommentDTO objects representing the comments.
     */
    @Override
    public CompletableFuture<List<CommentDTO>> getCommentsByPost(long postId) {
        return CompletableFuture.supplyAsync(() -> {
            List<CommentDTO> comments = new ArrayList<>();
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                String query = "SELECT c.*, p.*, u.* FROM Comment c " +
                               "JOIN Post p ON c.Post_Id = p.Id " +
                               "JOIN User u ON c.User_Id = u.Id " +
                               "WHERE c." + COL_POST_ID + " = ?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setLong(1, postId);
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            UserDTO user = new UserDTO(
                                rs.getLong("u.Id"),
                                rs.getString("u.Email"),
                                rs.getString("u.Fullname"),
                                rs.getString("u.Image_Url"),
                                EAccountStatus.valueOf(rs.getString("u.Status")),
                                EAccountRole.valueOf(rs.getString("u.Role")),
                                rs.getByte("u.Age"),
                                rs.getLong("u.Gender_Id"),
                                rs.getLong("u.Preference_Id"),
                                rs.getString("u.Nickname")
                            );
                            PostDTO post = new PostDTO(
                                    rs.getLong("p.Id"),
                                    user,
                                    rs.getString("p.Content"),
                                    rs.getTimestamp("p.Created_At").toLocalDateTime(),
                                    rs.getInt("p.Hearts_Total"),
                                    rs.getInt("p.Comment_Total"),
                                    rs.getString("p.Status"),
                                    rs.getString("p.Image_Url")
                            );
                            CommentDTO reply = getCommentById(rs.getLong(COL_REPLY_ID)).join();
                            comments.add(new CommentDTO(
                                rs.getLong("c." + COL_ID),
                                post,
                                user,
                                rs.getString("c." + COL_CONTENT),
                                rs.getTimestamp("c." + COL_CREATED_AT).toLocalDateTime(),
                                rs.getString("c." + COL_STATUS),
                                reply
                            ));
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Cannot get comments by post id: " + ex.getMessage());
            }
            return comments;
        });
    }

    /**
     * Retrieves all child comments of a comment.
     *
     * @param id The ID of the comment to retrieve child comments for.
     * @return A list of CommentDTO objects representing the child comments.
     */
    @Override
    public CompletableFuture<List<CommentDTO>> getChildComments(long id) {
        return CompletableFuture.supplyAsync(() -> {
            List<CommentDTO> childComments = new ArrayList<>();
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                String query = "SELECT c.*, p.*, u.* FROM Comment c " +
                               "JOIN Post p ON c.Post_Id = p.Id " +
                               "JOIN User u ON c.User_Id = u.Id " +
                               "WHERE c." + COL_REPLY_ID + " = ?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setLong(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            UserDTO user = new UserDTO(
                                rs.getLong("u.Id"),
                                rs.getString("u.Email"),
                                rs.getString("u.Fullname"),
                                rs.getString("u.Image_Url"),
                                EAccountStatus.valueOf(rs.getString("u.Status")),
                                EAccountRole.valueOf(rs.getString("u.Role")),
                                rs.getByte("u.Age"),
                                rs.getLong("u.Gender_Id"),
                                rs.getLong("u.Preference_Id"),
                                rs.getString("u.Nickname")
                            );
                            PostDTO post = new PostDTO(
                                rs.getLong("p.Id"),
                                user,
                                rs.getString("p.Content"),
                                rs.getTimestamp("p.Created_At").toLocalDateTime(),
                                rs.getInt("p.Hearts_Total"),
                                rs.getInt("p.Comment_Total"),
                                rs.getString("p.Status"),
                                rs.getString("p.Image_Url")
                            );
                            CommentDTO reply = getCommentById(rs.getLong(COL_REPLY_ID)).join();
                            childComments.add(new CommentDTO(
                                rs.getLong("c." + COL_ID),
                                post,
                                user,
                                rs.getString("c." + COL_CONTENT),
                                rs.getTimestamp("c." + COL_CREATED_AT).toLocalDateTime(),
                                rs.getString("c." + COL_STATUS),
                                reply
                            ));
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Cannot get child comments: " + ex.getMessage());
            }
            return childComments;
        });
    }

    /**
     * Inserts a new comment into the database.
     *
     * @param comment The CommentDTO object containing the comment data to be inserted.
     */
    @Override
    public CompletableFuture<Void> insertComment(CommentDTO comment) {
        return CompletableFuture.runAsync(() -> {
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Comment (" + COL_POST_ID + ", " + COL_USER_ID + ", " + COL_CONTENT + ", " + COL_CREATED_AT + ", " + COL_STATUS + ", " + COL_REPLY_ID + ") VALUES (?, ?, ?, ?, ?, ?)")) {
                    ps.setLong(1, comment.getPost().getId());
                    ps.setLong(2, comment.getUser().getId());
                    ps.setString(3, comment.getContent());
                    ps.setTimestamp(4, java.sql.Timestamp.valueOf(comment.getCreatedAt()));
                    ps.setString(5, comment.getStatus());
                    ps.setLong(6, comment.getReply().getId());
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                System.out.println("Cannot insert comment: " + ex.getMessage());
            }
        });
    }

    /**
     * Updates a comment in the database.
     *
     * @param comment The CommentDTO object containing the updated comment data.
     */
    @Override
    public CompletableFuture<Void> updateComment(CommentDTO comment) {
        return CompletableFuture.runAsync(() -> {
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                try (PreparedStatement ps = conn.prepareStatement("UPDATE Comment SET " + COL_POST_ID + " = ?, " + COL_USER_ID + " = ?, " + COL_CONTENT + " = ?, " + COL_CREATED_AT + " = ?, " + COL_STATUS + " = ?, " + COL_REPLY_ID + " = ? WHERE " + COL_ID + " = ?")) {
                    ps.setLong(1, comment.getPost().getId());
                    ps.setLong(2, comment.getUser().getId());
                    ps.setString(3, comment.getContent());
                    ps.setTimestamp(4, java.sql.Timestamp.valueOf(comment.getCreatedAt()));
                    ps.setString(5, comment.getStatus());
                    ps.setLong(6, comment.getReply().getId());
                    ps.setLong(7, comment.getId());
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                System.out.println("Cannot update comment: " + ex.getMessage());
            }
        });
    }

    /**
     * Deletes a comment from the database.
     *
     * @param id The ID of the comment to delete.
     */
    @Override
    public CompletableFuture<Void> deleteComment(long id) {
        return CompletableFuture.runAsync(() -> {
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Comment WHERE " + COL_ID + " = ?")) {
                    ps.setLong(1, id);
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                System.out.println("Cannot delete comment: " + ex.getMessage());
            }
        });
    }
}