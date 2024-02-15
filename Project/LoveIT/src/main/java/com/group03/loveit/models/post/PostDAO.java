package com.group03.loveit.models.post;

import com.group03.loveit.utilities.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class provides the Data Access Object (DAO) for the Post entity.
 * It provides methods to interact with the database such as retrieving, inserting, updating, and deleting posts.
 *
 * @author Nhat
 */
public class PostDAO implements IPostDAO {
    // ===========================
    // == Fields
    // ===========================
    private static final String COL_ID = "Id";
    private static final String COL_USER_ID = "User_Id";
    private static final String COL_CONTENT = "Content";
    private static final String COL_CREATED_AT = "Created_At";
    private static final String COL_HEARTS_TOTAL = "Hearts_Total";
    private static final String COL_COMMENT_TOTAL = "Comment_Total";
    private static final String COL_STATUS = "Status";
    private static final String COL_IMAGE_URL = "Image_Url";

    // ===========================
    // == Override Methods
    // ===========================
    /**
     * Retrieves a post by its ID.
     *
     * @param id The ID of the post to retrieve.
     * @return The PostDTO object representing the post, or null if no post with the given ID was found.
     */
    @Override
    public PostDTO getPostById(long id) {
        try (Connection conn = DBUtils.getConnection()) {
            if (conn == null) {
                throw new SQLException();
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Post WHERE " + COL_ID + " = ?")) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new PostDTO(
                                rs.getLong(COL_ID),
                                rs.getLong(COL_USER_ID),
                                rs.getString(COL_CONTENT),
                                rs.getString(COL_CREATED_AT),
                                rs.getInt(COL_HEARTS_TOTAL),
                                rs.getInt(COL_COMMENT_TOTAL),
                                rs.getString(COL_STATUS),
                                rs.getString(COL_IMAGE_URL)
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Cannot get post by id: " + ex.getMessage());
        }
        return null;
    }

    /**
     * Inserts a new post into the database.
     *
     * @param post The PostDTO object containing the post data to be inserted.
     */
    @Override
    public void insertPost(PostDTO post) {
        try (Connection conn = DBUtils.getConnection()) {
            if (conn == null) {
                throw new SQLException();
            }
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Post (" + COL_USER_ID + ", " + COL_CONTENT + ", " + COL_CREATED_AT + ", " + COL_HEARTS_TOTAL + ", " + COL_COMMENT_TOTAL + ", " + COL_STATUS + ", " + COL_IMAGE_URL + ") VALUES (?, ?, ?, ?, ?, ?, ?)")) {
                ps.setLong(1, post.getUserId());
                ps.setString(2, post.getContent());
                ps.setString(3, post.getCreatedAt());
                ps.setInt(4, post.getHeartTotal());
                ps.setInt(5, post.getCommentTotal());
                ps.setString(6, post.getStatus());
                ps.setString(7, post.getImageUrl());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot insert post: " + ex.getMessage());
        }
    }

    /**
     * Updates a post in the database.
     *
     * @param post The PostDTO object containing the updated post data.
     */
    @Override
    public void updatePost(PostDTO post) {
        try (Connection conn = DBUtils.getConnection()) {
            if (conn == null) {
                throw new SQLException();
            }
            try (PreparedStatement ps = conn.prepareStatement("UPDATE Post SET " + COL_USER_ID + " = ?, " + COL_CONTENT + " = ?, " + COL_CREATED_AT + " = ?, " + COL_HEARTS_TOTAL + " = ?, " + COL_COMMENT_TOTAL + " = ?, " + COL_STATUS + " = ?, " + COL_IMAGE_URL + " = ? WHERE " + COL_ID + " = ?")) {
                ps.setLong(1, post.getUserId());
                ps.setString(2, post.getContent());
                ps.setString(3, post.getCreatedAt());
                ps.setInt(4, post.getHeartTotal());
                ps.setInt(5, post.getCommentTotal());
                ps.setString(6, post.getStatus());
                ps.setString(7, post.getImageUrl());
                ps.setLong(8, post.getId());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot update post: " + ex.getMessage());
        }
    }

    /**
     * Deletes a post from the database.
     *
     * @param id The ID of the post to delete.
     */
    @Override
    public void deletePost(long id) {
        try (Connection conn = DBUtils.getConnection()) {
            if (conn == null) {
                throw new SQLException();
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Post WHERE " + COL_ID + " = ?")) {
                ps.setLong(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot delete post: " + ex.getMessage());
        }
    }
}