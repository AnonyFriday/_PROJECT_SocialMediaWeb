package com.group03.loveit.models.favourite;

import com.group03.loveit.utilities.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
     * @return The FavouriteDTO object representing the favourite, or null if no favourite with the given ID was found.
     */
    @Override
    public FavouriteDTO getFavouriteById(long postId, long userId) {
        try (Connection conn = DBUtils.getConnection()) {
            if (conn == null) {
                throw new SQLException();
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Favorite WHERE " + COL_POST_ID + " = ? AND " + COL_USER_ID + " = ?")) {
                ps.setLong(1, postId);
                ps.setLong(2, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new FavouriteDTO(
                                rs.getLong(COL_POST_ID),
                                rs.getLong(COL_USER_ID)
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Cannot get favourite by id: " + ex.getMessage());
        }
        return null;
    }

    /**
     * Retrieves all favourites by a user.
     *
     * @param userId The ID of the user to retrieve.
     * @return A list of FavouriteDTO objects representing the favourites, or an empty list if no favourites were found.
     */
    @Override
    public CompletableFuture<List<FavouriteDTO>> getFavouritesByUser(long userId) {
        return CompletableFuture.supplyAsync(() -> {
            try (Connection conn = DBUtils.getConnection()) {
                if (conn == null) {
                    throw new SQLException();
                }
                try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Favorite WHERE " + COL_USER_ID + " = ?")) {
                    ps.setLong(1, userId);
                    try (ResultSet rs = ps.executeQuery()) {
                        List<FavouriteDTO> favourites = new java.util.ArrayList<>();
                        while (rs.next()) {
                            favourites.add(new FavouriteDTO(
                                    rs.getLong(COL_POST_ID),
                                    rs.getLong(COL_USER_ID)
                            ));
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
     * Inserts a new favourite into the database.
     *
     * @param favourite The FavouriteDTO object containing the favourite data to be inserted.
     */
    @Override
    public void insertFavourite(FavouriteDTO favourite) {
        try (Connection conn = DBUtils.getConnection()) {
            if (conn == null) {
                throw new SQLException();
            }
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Favorite (" + COL_POST_ID + ", " + COL_USER_ID + ") VALUES (?, ?)")) {
                ps.setLong(1, favourite.getPostId());
                ps.setLong(2, favourite.getUserId());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot insert favourite: " + ex.getMessage());
        }
    }

    /**
     * Deletes a favourite from the database.
     *
     * @param postId The ID of the post to delete.
     * @param userId The ID of the user to delete.
     */
    @Override
    public void deleteFavourite(long postId, long userId) {
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
    }
}