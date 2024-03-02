package com.group03.loveit.models.post;

import com.group03.loveit.models.account.EAccountRole;
import com.group03.loveit.models.account.EAccountStatus;
import com.group03.loveit.models.gender.GenderDAO;
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
 * This class provides the Data Access Object (DAO) for the Post entity. It
 * provides methods to interact with the database such as retrieving, inserting,
 * updating, and deleting posts.
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
     * @return The CompletableFuture that returns the PostDTO object, or null if
     * the post is not found.
     */
    @Override
    public CompletableFuture<PostDTO> getPostById(long id) {
        return CompletableFuture.supplyAsync(() -> {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                conn = DBUtils.getConnection();
                if (conn == null) {
                    throw new RuntimeException("Connection is null");
                }
                String query = "SELECT p.Id as p_Id, p.Content as p_Content, p.Created_At as p_Created_At, p.Hearts_Total as p_Hearts_Total, p.Comment_Total as p_Comment_Total, p.Status as p_Status, p.Image_Url as p_Image_Url, "
                        + "u.Id as u_Id, u.Age as u_Age, u.Gender_Id as u_Gender_Id, u.Preference_Id as u_Preference_Id, u.Nickname as u_Nickname, u.Fullname as u_Fullname, u.Email as u_Email, u.Image_Url as u_Image_Url, u.Status as u_Status, u.Role as u_Role "
                        + "FROM Post p JOIN [User] u ON p.User_Id = u.Id WHERE p." + COL_ID + " = ?";

                ps = conn.prepareStatement(query);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    UserDTO user = new UserDTO(
                            rs.getLong("u_Id"),
                            rs.getByte("u_Age"),
                            GenderDAO.getInstance().getGenderMap().get(rs.getLong("u_Gender_Id")),
                            GenderDAO.getInstance().getGenderMap().get(rs.getLong("u_Preference_Id")),
                            rs.getString("u_Nickname"),
                            rs.getString("u_Fullname"),
                            rs.getString("u_Email"),
                            rs.getString("u_Image_Url"),
                            EAccountStatus.valueOf(rs.getString("u_Status")),
                            EAccountRole.valueOf(rs.getString("u_Role"))
                    );

                    return new PostDTO(
                            rs.getLong("p_Id"),
                            user,
                            rs.getString("p_Content"),
                            rs.getTimestamp("p_Created_At").toLocalDateTime(),
                            rs.getInt("p_Hearts_Total"),
                            rs.getInt("p_Comment_Total"),
                            rs.getString("p_Status"),
                            rs.getString("p_Image_Url")
                    );
                }
            } catch (SQLException ex) {
                System.out.println("Cannot get post by ID: " + ex.getMessage());
                ex.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("Error closing resources: " + ex.getMessage());
                }
            }
            return null;
        });
    }

    /**
     * Retrieves all posts from the database.
     *
     * @return A CompletableFuture that completes with a List of PostDTO objects
     * representing all posts in the database. Each PostDTO object contains the
     * post details and the associated user details. If there are no posts in
     * the database, the CompletableFuture completes with an empty list. If an
     * error occurs while retrieving the posts, the CompletableFuture completes
     * exceptionally with the SQLException.
     */
    @Override
    public CompletableFuture<List<PostDTO>> getAllPosts() {
        return CompletableFuture.supplyAsync(() -> {
            List<PostDTO> posts = new ArrayList<>();
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new RuntimeException("Connection is null");
                }
                String query = "SELECT p.Id as p_Id, p.Content as p_Content, p.Created_At as p_Created_At, p.Hearts_Total as p_Hearts_Total, p.Comment_Total as p_Comment_Total, p.Status as p_Status, p.Image_Url as p_Image_Url, "
                        + "u.Id as u_Id, u.Age as u_Age, u.Gender_Id as u_Gender_Id, u.Preference_Id as u_Preference_Id, u.Nickname as u_Nickname, u.Fullname as u_Fullname, u.Email as u_Email, u.Image_Url as u_Image_Url, u.Status as u_Status, u.Role as u_Role "
                        + "FROM Post p JOIN [User] u ON p.User_Id = u.Id";

                try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        UserDTO user = new UserDTO(
                                rs.getLong("u_Id"),
                                rs.getByte("u_Age"),
                                GenderDAO.getInstance().getGenderMap().get(rs.getLong("u_Gender_Id")),
                                GenderDAO.getInstance().getGenderMap().get(rs.getLong("u_Preference_Id")),
                                rs.getString("u_Nickname"),
                                rs.getString("u_Fullname"),
                                rs.getString("u_Email"),
                                rs.getString("u_Image_Url"),
                                // EAccountStatus.valueOf(rs.getString("u_Status")),
                                // EAccountRole.valueOf(rs.getString("u_Role"))
                                EAccountStatus.ACTIVE, //! Having Error here on ENUM, will fix later
                                EAccountRole.ADMIN
                        );

                        PostDTO post = new PostDTO(
                                rs.getLong("p_Id"),
                                user,
                                rs.getString("p_Content"),
                                rs.getTimestamp("p_Created_At").toLocalDateTime(),
                                rs.getInt("p_Hearts_Total"),
                                rs.getInt("p_Comment_Total"),
                                rs.getString("p_Status"),
                                rs.getString("p_Image_Url")
                        );
                        posts.add(post);
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Cannot get posts: " + ex.getMessage());
            }
            return posts;
        });
    }

    /**
     * Inserts a new post into the database.
     *
     * @param post The PostDTO object containing the post data to be inserted.
     * @return The CompletableFuture that represents the completion of the
     * insertion.
     */
    @Override
    public CompletableFuture<Void> insertPost(PostDTO post) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Inserting post: " + post.toString());
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
                    System.out.println("Inserting post 2");
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
     * @return The CompletableFuture that represents the completion of the
     * update.
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
     * @return The CompletableFuture that represents the completion of the
     * deletion.
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

    // Testing
    public static void main(String[] args) {
        PostDAO post = new PostDAO();
        System.out.println(post.getAllPosts().join());
    }
}
