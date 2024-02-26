/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group03.loveit.models.user;

import com.group03.loveit.models.account.EAccountRole;
import com.group03.loveit.models.account.EAccountStatus;
import com.group03.loveit.models.gender.GenderDTO;
import com.group03.loveit.utilities.AsyncUtils;
import com.group03.loveit.utilities.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyvu
 */
public final class UserDAO implements IUserDAO {

    // ===========================
    // == Fields
    // ===========================
    private final String TABLE_NAME = "User";
    private final String COL_ID = "Id";
    private final String COL_EMAIL = "Email";
    private final String COL_NICKNAME = "Nickname";
    private final String COL_PASSWORD = "Password";
    private final String COL_FULLNAME = "FullName";
    private final String COL_IMAGEURL = "Image_Url";
    private final String COL_STATUS = "Status";
    private final String COL_ROLE = "Role";
    private final String COL_AGE = "Age";
    private final String COL_GENDER = "Gender_Id";
    private final String COL_GENDER_PREFERENCE = "Preference_Id";

    // ===========================
    // == Methods
    // ===========================
    // ===========================
    // == Override Methods
    // ===========================
    /**
     * Using Async Function to retrive extensive User list
     *
     * @return a future containing the list of users
     */
    @Override
    public CompletableFuture<List<UserDTO>> getUsers() {
        CompletableFuture<List<UserDTO>> future = CompletableFuture.supplyAsync(() -> {

            Connection connection = null;
            PreparedStatement stmt = null;
            ResultSet resultSet = null;

            String sql = "SELECT * FROM " + TABLE_NAME;
            List<UserDTO> users = new ArrayList<>();

            try {
                connection = DBUtils.getConnection();
                stmt = connection.prepareStatement(sql);
                resultSet = stmt.executeQuery();

                if (resultSet != null && !resultSet.isClosed()) {
                    while (resultSet.next()) {
                        long id = resultSet.getLong(COL_ID);
                        String email = resultSet.getNString(COL_EMAIL);
                        String fullName = resultSet.getNString(COL_FULLNAME);
                        String nickName = resultSet.getNString(COL_NICKNAME);
                        String imageUrl = resultSet.getNString(COL_IMAGEURL);
                        EAccountStatus status = EAccountStatus.valueOf(resultSet.getString(COL_STATUS));
                        EAccountRole role = EAccountRole.valueOf(resultSet.getNString(COL_ROLE));
                        byte age = resultSet.getByte(COL_AGE);
                        long gender = resultSet.getLong(COL_GENDER);
                        long preferenceGender = resultSet.getInt(COL_GENDER_PREFERENCE);

                        // Add user to the array
                        UserDTO user = new UserDTO(id, email, fullName, imageUrl, status, role, age, gender, preferenceGender, nickName);
                        users.add(user);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                // Close resourse
                AsyncUtils.closeQuitely(stmt);
                AsyncUtils.closeQuitely(resultSet);
                AsyncUtils.closeQuitely(connection);
            }

            return users;
        });
        return future;
    }

    /**
     * Getting User based on certain criteria
     *
     * @param usc
     * @return
     */
    @Override
    public CompletableFuture<List<UserDTO>> getUsersByConditions(UserSearchCriteria usc) {

        // Using Future for async data retrieve
        CompletableFuture<List<UserDTO>> future = CompletableFuture.supplyAsync(() -> {

            // WHERE 1=1 attached WHERE keyword in any scenario
            String sql = "SELECT * FROM " + TABLE_NAME;
            sql += " WHERE 1=1 ";

            if (usc.getFullname() != null) {
                sql += " AND " + COL_FULLNAME + " LIKE ? ";
            }

            if (usc.getNickName() != null) {
                sql += " OR " + COL_NICKNAME + " LIKE ? ";
            }

            List<UserDTO> users = new ArrayList<>();
            Connection connection = null;
            PreparedStatement stmt = null;
            ResultSet resultSet = null;

            // Conducting the comments
            try {
                connection = DBUtils.getConnection();
                stmt = connection.prepareStatement(sql);

                stmt.setString(1, sql);

                if (usc.getFullname() != null) {
                    stmt.setString(1, usc.getFullname());
                }

                if (usc.getNickName() != null) {
                    stmt.setString(2, usc.getNickName());
                }

                ResultSet rs = stmt.executeQuery();

                if (resultSet != null && !resultSet.isClosed()) {
                    while (resultSet.next()) {
                        long id = resultSet.getLong(COL_ID);
                        String email = resultSet.getNString(COL_EMAIL);
                        String fullName = resultSet.getNString(COL_FULLNAME);
                        String nickName = resultSet.getNString(COL_NICKNAME);
                        String imageUrl = resultSet.getNString(COL_IMAGEURL);
                        EAccountStatus status = EAccountStatus.valueOf(resultSet.getString(COL_STATUS));
                        EAccountRole role = EAccountRole.valueOf(resultSet.getNString(COL_ROLE));
                        byte age = resultSet.getByte(COL_AGE);
                        long gender = resultSet.getLong(COL_GENDER);
                        long preferenceGender = resultSet.getInt(COL_GENDER_PREFERENCE);

                        // Add user to the array
                        UserDTO user = new UserDTO(id, email, fullName, imageUrl, status, role, age, gender, preferenceGender, nickName);
                        users.add(user);
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            return users;
        });

        return future;
    }

    @Override
    public UserDTO getUserById(long l) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(UserDTO udto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(UserDTO udto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(long l) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
