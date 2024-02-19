package com.group03.loveit.models.favourite;

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
 * This class provides the Data Access Object (DAO) for the Favourite entity.
 * It provides methods to interact with the database such as retrieving, inserting, and deleting favourites.
 *
 * @author Nhat
 */
public class FavouriteDAO implements IFavouriteDAO {
    // ===========================
    // == Fields
    // ===========================
    private static final String COL_POST_ID = "Post_Id";
    private static final String COL_USER_ID = "User_Id";

    // ===========================
    // == Override Methods
    // ===========================

    /**
     * Retrieves a favourite by its post ID and user ID.
     *
     * @param postId The ID of the post to retrieve.
     * @param userId The ID of the user to retrieve.
     * @return The CompletableFuture that returns the FavouriteDTO object, or null if the favourite is not found.
     */
    @Override
    public CompletableFuture<FavouriteDTO> getFavouriteById(long postId, long userId) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                String query = "SELECT f.*, p.*, u.* FROM Favorite f " +
                        "JOIN Post p ON f." + COL_POST_ID + " = p.Id " +
                        "JOIN User u ON f." + COL_USER_ID + " = u.Id " +
                        "WHERE f." + COL_POST_ID + " = ? AND f." + COL_USER_ID + " = ?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setLong(1, postId);
                    ps.setLong(2, userId);
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
                            return new FavouriteDTO(post, user);
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Cannot get favourite by id: " + ex.getMessage());
            }
            return null;
        });
    }

    /**
     * Retrieves all favourites by a user.
     *
     * @param userId The ID of the user to retrieve.
     * @return The CompletableFuture that returns a list of FavouriteDTO objects, or empty list if the user has no favourites.
     */
    @Override
    public CompletableFuture<List<FavouriteDTO>> getFavouritesByUser(long userId) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                String query = "SELECT f.*, p.*, u.* FROM Favorite f " +
                               "JOIN Post p ON f." + COL_POST_ID + " = p.Id " +
                               "JOIN User u ON f." + COL_USER_ID + " = u.Id " +
                               "WHERE f." + COL_USER_ID + " = ?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setLong(1, userId);
                    try (ResultSet rs = ps.executeQuery()) {
                        List<FavouriteDTO> favourites = new ArrayList<>();
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
                            favourites.add(new FavouriteDTO(post, user));
                        }
                        return favourites;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Cannot get favourites by user: " + ex.getMessage());
            }
            return null;
        });
    }

    /**
     * Retrieves all favourites by a post.
     *
     * @param postId The ID of the post to retrieve.
     * @return The CompletableFuture that returns the list of FavouriteDTO objects, or empty list if no user has favourited the post.
     */
    @Override
    public CompletableFuture<List<FavouriteDTO>> getFavouritesByPost(long postId) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                String query = "SELECT f.*, p.*, u.* FROM Favorite f " +
                               "JOIN Post p ON f." + COL_POST_ID + " = p.Id " +
                               "JOIN User u ON f." + COL_USER_ID + " = u.Id " +
                               "WHERE f." + COL_POST_ID + " = ?";
                try (PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setLong(1, postId);
                    try (ResultSet rs = ps.executeQuery()) {
                        List<FavouriteDTO> favourites = new ArrayList<>();
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
                            favourites.add(new FavouriteDTO(post, user));
                        }
                        return favourites;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Cannot get favourites by post: " + ex.getMessage());
            }
            return null;
        });
    }

    /**
     * Inserts a new favourite into the database.
     *
     * @param favourite The FavouriteDTO object containing the favourite data to be inserted.
     * @return The CompletableFuture that represents the completion of the insertion.
     */
    @Override
    public CompletableFuture<Void> insertFavourite(FavouriteDTO favourite) {
        return CompletableFuture.runAsync(() -> {
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Favorite (" + COL_POST_ID + ", " + COL_USER_ID + ") VALUES (?, ?)")) {
                    ps.setLong(1, favourite.getPost().getId());
                    ps.setLong(2, favourite.getUser().getId());
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                System.out.println("Cannot insert favourite: " + ex.getMessage());
            }
        });
    }

    /**
     * Deletes a favourite from the database.
     *
     * @param postId The ID of the post to delete.
     * @param userId The ID of the user to delete.
     * @return The CompletableFuture that represents the completion of the deletion.
     */
    @Override
    public CompletableFuture<Void> deleteFavourite(long postId, long userId) {
        return CompletableFuture.runAsync(() -> {
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Favorite WHERE " + COL_POST_ID + " = ? AND " + COL_USER_ID + " = ?")) {
                    ps.setLong(1, postId);
                    ps.setLong(2, userId);
                    ps.executeUpdate();
                }
            } catch (SQLException ex) {
                System.out.println("Cannot delete favourite: " + ex.getMessage());
            }
        });
    }
}