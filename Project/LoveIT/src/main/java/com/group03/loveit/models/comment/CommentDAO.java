package com.group03.loveit.models.comment;

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
    private static final String COL_ID = "Id";
    private static final String COL_POST_ID = "Post_Id";
    private static final String COL_USER_ID = "User_Id";
    private static final String COL_CONTENT = "Content";
    private static final String COL_CREATED_AT = "Created_At";
    private static final String COL_STATUS = "Status";
    private static final String COL_REPLY_ID = "Reply_Id";

    /**
     * Retrieves a comment by its ID.
     *
     * @param id The ID of the comment to retrieve.
     * @return The CommentDTO object representing the comment, or null if no comment with the given ID was found.
     */
    @Override
    public CommentDTO getCommentById(long id) {
        try (Connection conn = DBUtils.getConnection()) {
            if (conn == null) {
                throw new SQLException();
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Comment WHERE " + COL_ID + " = ?")) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new CommentDTO(
                                rs.getLong(COL_ID),
                                rs.getLong(COL_POST_ID),
                                rs.getLong(COL_USER_ID),
                                rs.getString(COL_CONTENT),
                                rs.getTimestamp(COL_CREATED_AT).toLocalDateTime(),
                                rs.getString(COL_STATUS),
                                rs.getLong(COL_REPLY_ID)
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Cannot get comment by id: " + ex.getMessage());
        }
        return null;
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
                try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Comment WHERE " + COL_POST_ID + " = ?")) {
                    ps.setLong(1, postId);
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            comments.add(new CommentDTO(
                                    rs.getLong(COL_ID),
                                    rs.getLong(COL_POST_ID),
                                    rs.getLong(COL_USER_ID),
                                    rs.getString(COL_CONTENT),
                                    rs.getTimestamp(COL_CREATED_AT).toLocalDateTime(),
                                    rs.getString(COL_STATUS),
                                    rs.getLong(COL_REPLY_ID)
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
                try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Comment WHERE " + COL_REPLY_ID + " = ?")) {
                    ps.setLong(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            childComments.add(new CommentDTO(
                                    rs.getLong(COL_ID),
                                    rs.getLong(COL_POST_ID),
                                    rs.getLong(COL_USER_ID),
                                    rs.getString(COL_CONTENT),
                                    rs.getTimestamp(COL_CREATED_AT).toLocalDateTime(),
                                    rs.getString(COL_STATUS),
                                    rs.getLong(COL_REPLY_ID)
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
    public void insertComment(CommentDTO comment) {
        try (Connection conn = DBUtils.getConnection()) {
            if (conn == null) {
                throw new SQLException();
            }
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Comment (" + COL_POST_ID + ", " + COL_USER_ID + ", " + COL_CONTENT + ", " + COL_CREATED_AT + ", " + COL_STATUS + ", " + COL_REPLY_ID + ") VALUES (?, ?, ?, ?, ?, ?)")) {
                ps.setLong(1, comment.getPostId());
                ps.setLong(2, comment.getUserId());
                ps.setString(3, comment.getContent());
                ps.setTimestamp(4, java.sql.Timestamp.valueOf(comment.getCreatedAt()));
                ps.setString(5, comment.getStatus());
                ps.setLong(6, comment.getReplyId());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot insert comment: " + ex.getMessage());
        }
    }

    /**
     * Updates a comment in the database.
     *
     * @param comment The CommentDTO object containing the updated comment data.
     */
    @Override
    public void updateComment(CommentDTO comment) {
        try (Connection conn = DBUtils.getConnection()) {
            if (conn == null) {
                throw new SQLException();
            }
            try (PreparedStatement ps = conn.prepareStatement("UPDATE Comment SET " + COL_POST_ID + " = ?, " + COL_USER_ID + " = ?, " + COL_CONTENT + " = ?, " + COL_CREATED_AT + " = ?, " + COL_STATUS + " = ?, " + COL_REPLY_ID + " = ? WHERE " + COL_ID + " = ?")) {
                ps.setLong(1, comment.getPostId());
                ps.setLong(2, comment.getUserId());
                ps.setString(3, comment.getContent());
                ps.setTimestamp(4, java.sql.Timestamp.valueOf(comment.getCreatedAt()));
                ps.setString(5, comment.getStatus());
                ps.setLong(6, comment.getReplyId());
                ps.setLong(7, comment.getId());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot update comment: " + ex.getMessage());
        }
    }

    /**
     * Deletes a comment from the database.
     *
     * @param id The ID of the comment to delete.
     */
    @Override
    public void deleteComment(long id) {
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
    }
}