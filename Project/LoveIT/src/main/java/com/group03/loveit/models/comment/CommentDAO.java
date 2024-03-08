package com.group03.loveit.models.comment;

import com.group03.loveit.models.account.EAccountRole;
import com.group03.loveit.models.account.EAccountStatus;
import com.group03.loveit.models.gender.GenderDAO;
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
import java.util.concurrent.ExecutionException;

/**
 * This class provides the Data Access Object (DAO) for the Comment entity. It
 * provides methods to interact with the database such as retrieving, inserting,
 * updating, and deleting comments.
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
     * @return The CompletableFuture that returns the CommentDTO object, or null
     * if the comment is not found.
     */
    @Override
    public CompletableFuture<CommentDTO> getCommentById(long id) {
        return CompletableFuture.supplyAsync(() -> {
            CommentDTO comment = null;
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                String query = "SELECT c.*, "
                        + "p.Id as p_id, p.Content as p_content, p.Created_At as p_created_at, p.Status as p_status, p.Image_Url as p_image_url, p.*, "
                        + "u.Id as u_id, u.Image_Url as u_image_url, u.Status as u_status, u.* "
                        + "FROM Comment c "
                        + "JOIN Post p ON c.Post_Id = p.Id "
                        + "JOIN [User] u ON c.User_Id = u.Id "
                        + "WHERE c." + COL_ID + " = ?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setLong(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            UserDTO user = new UserDTO(
                                    rs.getLong("u_id"),
                                    rs.getByte("Age"),
                                    GenderDAO.getInstance().getGenderMap().get(rs.getLong("Gender_Id")),
                                    GenderDAO.getInstance().getGenderMap().get(rs.getLong("Preference_Id")),
                                    rs.getString("Nickname"),
                                    rs.getString("Fullname"),
                                    rs.getString("Email"),
                                    rs.getString("u_image_url"),
                                    EAccountStatus.getEnumFromName(rs.getString("u_status")),
                                    EAccountRole.getEnumFromName(rs.getString("Role")));
                            PostDTO post = new PostDTO(
                                    rs.getLong("p_id"),
                                    user,
                                    rs.getString("p_content"),
                                    rs.getTimestamp("p_created_at").toLocalDateTime(),
                                    rs.getInt("Hearts_Total"),
                                    rs.getInt("Comment_Total"),
                                    rs.getString("p_status"),
                                    rs.getString("p_image_url")
                            );
                            CommentDTO reply = getCommentById(rs.getLong(COL_REPLY_ID)).join();
                            comment = new CommentDTO(
                                    rs.getLong(COL_ID),
                                    post,
                                    user,
                                    rs.getString(COL_CONTENT),
                                    rs.getTimestamp(COL_CREATED_AT).toLocalDateTime(),
                                    rs.getString(COL_STATUS),
                                    reply
                            );
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Cannot get comment by id: " + ex.getMessage());
            }
            return comment;
        });
    }

    /**
     * Retrieves all comments associated with a post.
     *
     * @param postId The ID of the post to retrieve comments for.
     * @return The CompletableFuture that returns a list of CommentDTO objects
     * representing the comments, or an empty list if no comments are found.
     */
    @Override
    public CompletableFuture<List<CommentDTO>> getCommentsByPost(long postId) {
        return CompletableFuture.supplyAsync(() -> {
            List<CommentDTO> comments = new ArrayList<>();
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                String query = "SELECT c.*, "
                        + "p.Id as p_id, p.Content as p_content, p.Created_At as p_created_at, p.Status as p_status, p.Image_Url as p_image_url, p.*, "
                        + "u.Id as u_id, u.Image_Url as u_image_url, u.Status as u_status, u.* "
                        + "FROM Comment c "
                        + "JOIN Post p ON c.Post_Id = p.Id "
                        + "JOIN [User] u ON c.User_Id = u.Id "
                        + "WHERE c." + COL_POST_ID + " = ?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setLong(1, postId);
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            UserDTO user = new UserDTO(
                                    rs.getLong("u_id"),
                                    rs.getByte("Age"),
                                    GenderDAO.getInstance().getGenderMap().get(rs.getLong("Gender_Id")),
                                    GenderDAO.getInstance().getGenderMap().get(rs.getLong("Preference_Id")),
                                    rs.getString("Nickname"),
                                    rs.getString("Fullname"),
                                    rs.getString("Email"),
                                    rs.getString("u_image_url"),
                                    EAccountStatus.getEnumFromName(rs.getString("u_status")),
                                    EAccountRole.getEnumFromName(rs.getString("Role")));
                            PostDTO post = new PostDTO(
                                    rs.getLong("p_id"),
                                    user,
                                    rs.getString("p_content"),
                                    rs.getTimestamp("p_created_at").toLocalDateTime(),
                                    rs.getInt("Hearts_Total"),
                                    rs.getInt("Comment_Total"),
                                    rs.getString("p_status"),
                                    rs.getString("p_image_url")
                            );
                            CommentDTO reply = getCommentById(rs.getLong(COL_REPLY_ID)).get();
                            comments.add(new CommentDTO(
                                    rs.getLong(COL_ID),
                                    post,
                                    user,
                                    rs.getString(COL_CONTENT),
                                    rs.getTimestamp(COL_CREATED_AT).toLocalDateTime(),
                                    rs.getString(COL_STATUS),
                                    reply
                            ));
                        }
                    }
                }
            } catch (SQLException | InterruptedException | ExecutionException e) {
                System.out.println("Cannot get comments by post id: " + e.getMessage());
            }
            return comments;
        });
    }

    /**
     * Retrieves the first comment of a post from the database.
     *
     * @param postId The ID of the post to retrieve the top comment for.
     * @return The CompletableFuture that returns the CommentDTO object representing the top comment, or null if the post has no comments.
     */
    @Override
    public CompletableFuture<CommentDTO> getTopCommentByPost(long postId) {
        return CompletableFuture.supplyAsync(() -> {
            CommentDTO topComment = null;
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                String query = "SELECT TOP 1 c.*, "
                        + "p.Id as p_id, p.Content as p_content, p.Created_At as p_created_at, p.Status as p_status, p.Image_Url as p_image_url, p.*, "
                        + "u.Id as u_id, u.Image_Url as u_image_url, u.Status as u_status, u.* "
                        + "FROM Comment c "
                        + "JOIN Post p ON c.Post_Id = p.Id "
                        + "JOIN [User] u ON c.User_Id = u.Id "
                        + "WHERE c." + COL_POST_ID + " = ? "
                        + "ORDER BY c.Created_At ASC";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setLong(1, postId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            UserDTO user = new UserDTO(
                                    rs.getLong("u_id"),
                                    rs.getByte("Age"),
                                    GenderDAO.getInstance().getGenderMap().get(rs.getLong("Gender_Id")),
                                    GenderDAO.getInstance().getGenderMap().get(rs.getLong("Preference_Id")),
                                    rs.getString("Nickname"),
                                    rs.getString("Fullname"),
                                    rs.getString("Email"),
                                    rs.getString("u_image_url"),
                                    EAccountStatus.getEnumFromName(rs.getString("u_status")),
                                    EAccountRole.getEnumFromName(rs.getString("Role")));
                            PostDTO post = new PostDTO(
                                    rs.getLong("p_id"),
                                    user,
                                    rs.getString("p_content"),
                                    rs.getTimestamp("p_created_at").toLocalDateTime(),
                                    rs.getInt("Hearts_Total"),
                                    rs.getInt("Comment_Total"),
                                    rs.getString("p_status"),
                                    rs.getString("p_image_url")
                            );
                            CommentDTO reply = getCommentById(rs.getLong(COL_REPLY_ID)).join();
                            topComment = new CommentDTO(
                                    rs.getLong(COL_ID),
                                    post,
                                    user,
                                    rs.getString(COL_CONTENT),
                                    rs.getTimestamp(COL_CREATED_AT).toLocalDateTime(),
                                    rs.getString(COL_STATUS),
                                    reply
                            );
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Cannot get top comment by post id: " + ex.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
            return topComment;
        });
    }

    /**
     * Retrieves all child comments of a comment.
     *
     * @param id The ID of the comment to retrieve child comments for.
     * @return The CompletableFuture that returns a list of CommentDTO objects
     * representing the child comments, or an empty list if no child comments
     * are found.
     */
    @Override
    public CompletableFuture<List<CommentDTO>> getChildComments(long id) {
        return CompletableFuture.supplyAsync(() -> {
            List<CommentDTO> childComments = new ArrayList<>();
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                String query = "SELECT c.*, "
                        + "p.Id as p_id, p.Content as p_content, p.Created_At as p_created_at, p.Status as p_status, p.Image_Url as p_image_url, p.*, "
                        + "u.Id as u_id, u.Image_Url as u_image_url, u.Status as u_status, u.* "
                        + "FROM Comment c "
                        + "JOIN Post p ON c.Post_Id = p.Id "
                        + "JOIN [User] u ON c.User_Id = u.Id "
                        + "WHERE c." + COL_REPLY_ID + " = ?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setLong(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            UserDTO user = new UserDTO(
                                    rs.getLong("u_id"),
                                    rs.getByte("Age"),
                                    GenderDAO.getInstance().getGenderMap().get(rs.getLong("Gender_Id")),
                                    GenderDAO.getInstance().getGenderMap().get(rs.getLong("Preference_Id")),
                                    rs.getString("Nickname"),
                                    rs.getString("Fullname"),
                                    rs.getString("Email"),
                                    rs.getString("u_image_url"),
                                    EAccountStatus.getEnumFromName(rs.getString("u_status")),
                                    EAccountRole.getEnumFromName(rs.getString("Role")));
                            PostDTO post = new PostDTO(
                                    rs.getLong("p_id"),
                                    user,
                                    rs.getString("p_content"),
                                    rs.getTimestamp("p_created_at").toLocalDateTime(),
                                    rs.getInt("Hearts_Total"),
                                    rs.getInt("Comment_Total"),
                                    rs.getString("p_status"),
                                    rs.getString("p_image_url")
                            );
                            CommentDTO reply = getCommentById(rs.getLong(COL_REPLY_ID)).join();
                            childComments.add(new CommentDTO(
                                    rs.getLong(COL_ID),
                                    post,
                                    user,
                                    rs.getString(COL_CONTENT),
                                    rs.getTimestamp(COL_CREATED_AT).toLocalDateTime(),
                                    rs.getString(COL_STATUS),
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
     * @param comment The CommentDTO object containing the comment data to be
     * inserted.
     * @return The CompletableFuture that represents the completion of the
     * insertion.
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
                    if (comment.getReply() == null) {
                        ps.setNull(6, java.sql.Types.BIGINT);
                    } else {
                        ps.setLong(6, comment.getReply().getId());
                    }
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                System.out.println("Error inserting comment: " + ex.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        });
    }

    /**
     * Updates a comment in the database.
     *
     * @param comment The CommentDTO object containing the updated comment data.
     * @return The CompletableFuture that represents the completion of the
     * update.
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
     * @return The CompletableFuture that represents the completion of the
     * deletion.
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
