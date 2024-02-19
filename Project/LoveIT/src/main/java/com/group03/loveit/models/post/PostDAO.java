package com.group03.loveit.models.post;

import com.group03.loveit.models.account.EAccountRole;
import com.group03.loveit.models.account.EAccountStatus;
import com.group03.loveit.models.user.UserDTO;
import com.group03.loveit.utilities.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<PostDTO> getPostById(long id) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                try (PreparedStatement ps = conn.prepareStatement("SELECT p.*, u.* FROM Post p JOIN User u ON p.User_Id = u.Id WHERE p." + COL_ID + " = ?")) {
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
                            return new PostDTO(
                                    rs.getLong("p." + COL_ID),
                                    user,
                                    rs.getString("p." + COL_CONTENT),
                                    rs.getTimestamp("p." + COL_CREATED_AT).toLocalDateTime(),
                                    rs.getInt("p." + COL_HEARTS_TOTAL),
                                    rs.getInt("p." + COL_COMMENT_TOTAL),
                                    rs.getString("p." + COL_STATUS),
                                    rs.getString("p." + COL_IMAGE_URL)
                            );
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Cannot get post by id: " + ex.getMessage());
            }
            return null;
        });
    }

    /**
     * Inserts a new post into the database.
     *
     * @param post The PostDTO object containing the post data to be inserted.
     */
    @Override
    public CompletableFuture<Void> insertPost(PostDTO post) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Post (" + COL_USER_ID + ", " + COL_CONTENT + ", " + COL_CREATED_AT + ", " + COL_HEARTS_TOTAL + ", " + COL_COMMENT_TOTAL + ", " + COL_STATUS + ", " + COL_IMAGE_URL + ") VALUES (?, ?, ?, ?, ?, ?, ?)")) {
                    ps.setLong(1, post.getUser().getId());
                    ps.setString(2, post.getContent());
                    ps.setTimestamp(3, java.sql.Timestamp.valueOf(post.getCreatedAt()));
                    ps.setInt(4, post.getHeartTotal());
                    ps.setInt(5, post.getCommentTotal());
                    ps.setString(6, post.getStatus());
                    ps.setString(7, post.getImageUrl());
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                System.out.println("Cannot insert post: " + ex.getMessage());
            }
            return null;
        });
    }

    /**
     * Updates a post in the database.
     *
     * @param post The PostDTO object containing the updated post data.
     */
    @Override
    public CompletableFuture<Void> updatePost(PostDTO post) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                try (PreparedStatement ps = conn.prepareStatement("UPDATE Post SET " + COL_USER_ID + " = ?, " + COL_CONTENT + " = ?, " + COL_CREATED_AT + " = ?, " + COL_HEARTS_TOTAL + " = ?, " + COL_COMMENT_TOTAL + " = ?, " + COL_STATUS + " = ?, " + COL_IMAGE_URL + " = ? WHERE " + COL_ID + " = ?")) {
                    ps.setLong(1, post.getUser().getId());
                    ps.setString(2, post.getContent());
                    ps.setTimestamp(3, java.sql.Timestamp.valueOf(post.getCreatedAt()));
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
            return null;
        });
    }

    /**
     * Deletes a post from the database.
     *
     * @param id The ID of the post to delete.
     */
    @Override
    public CompletableFuture<Void> deletePost(long id) {
        return CompletableFuture.supplyAsync(() -> {
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
            return null;
        });
    }
}