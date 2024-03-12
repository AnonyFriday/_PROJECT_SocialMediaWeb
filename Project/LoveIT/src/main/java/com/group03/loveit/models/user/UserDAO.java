/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group03.loveit.models.user;

import com.group03.loveit.models.account.EAccountRole;
import com.group03.loveit.models.account.EAccountStatus;
import com.group03.loveit.models.gender.GenderDAO;
import com.group03.loveit.models.gender.GenderDTO;
import com.group03.loveit.utilities.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author duyvu
 */
public final class UserDAO implements IUserDAO {

    // ===========================
    // == Fields
    // ===========================
    private final String TABLE_NAME = "[User]";
    private final String COL_ID = "Id";
    private final String COL_EMAIL = "Email";
    private final String COL_PASSWORD = "Password";
    private final String COL_FULLNAME = "FullName";
    private final String COL_NICKNAME = "Nickname";
    private final String COL_IMAGEURL = "Image_Url";
    private final String COL_STATUS = "Status";
    private final String COL_ROLE = "Role";
    private final String COL_AGE = "Age";
    private final String COL_CREATE_AT = "Created_At";
    private final String COL_GENDER_ID = "Gender_Id";
    private final String COL_GENDER_PREFERENCE_ID = "Preference_Id";

    // ===========================
    // == Methods
    // ===========================
    public void registerAccountUser() {

    }

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
        return CompletableFuture.supplyAsync(() -> {
            try (Connection conn = DBUtils.getConnection()) {

                // SQL query
                String sql = " SELECT * "
                        + " FROM " + TABLE_NAME;

                List<UserDTO> list = new ArrayList<>();

                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            UserDTO user = new UserDTO(
                                    rs.getLong(COL_ID),
                                    rs.getByte(COL_AGE),
                                    GenderDAO.getInstance().getGenderMap().get(rs.getLong(COL_GENDER_ID)),
                                    GenderDAO.getInstance().getGenderMap().get(rs.getLong(COL_GENDER_PREFERENCE_ID)),
                                    rs.getNString(COL_NICKNAME),
                                    rs.getNString(COL_FULLNAME),
                                    rs.getString(COL_EMAIL),
                                    rs.getString(COL_IMAGEURL),
                                    EAccountStatus.getEnumFromName(rs.getString(COL_STATUS)),
                                    EAccountRole.getEnumFromName(rs.getString(COL_ROLE)));
                            list.add(user);
                        }

                        return list;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Cannot get list of users");
            } catch (RuntimeException ex) {
                ex.printStackTrace();
            }
            return null;
        });
    }

    /**
     * Getting User based on certain criteria
     *
     * @param usc
     * @return
     */
    @Override

    public CompletableFuture<List<UserDTO>> getUsersByConditions(UserSearchCriteria conditions) {

        // Using Future for async data retrieve
        CompletableFuture<List<UserDTO>> future = CompletableFuture.supplyAsync(() -> {

            // WHERE 1=1 attached WHERE keyword in any scenario
            String sql = "SELECT * FROM " + TABLE_NAME;
            sql += " WHERE 1=1 ";

            if (conditions.getFullname() != null) {
                sql += " AND " + COL_FULLNAME + " LIKE ? ";
            }

            if (conditions.getNickName() != null) {
                sql += " OR " + COL_NICKNAME + " LIKE ? ";
            }

            List<UserDTO> users = new ArrayList<>();

            // 1. Connection
            try (Connection conn = DBUtils.getConnection()) {

                List<UserDTO> list = new ArrayList<>();

                // 2. Prepare Statement
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    if (conditions.getFullname() != null) {
                        ps.setNString(1, conditions.getFullname());
                    }

                    if (conditions.getNickName() != null) {
                        ps.setNString(2, conditions.getNickName());
                    }

                    // 3. Execute Query
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            UserDTO user = new UserDTO(
                                    rs.getLong(COL_ID),
                                    rs.getByte(COL_AGE),
                                    GenderDAO.getInstance().getGenderMap().get(rs.getLong(COL_GENDER_ID)),
                                    GenderDAO.getInstance().getGenderMap().get(rs.getLong(COL_GENDER_PREFERENCE_ID)),
                                    rs.getNString(COL_NICKNAME),
                                    rs.getNString(COL_FULLNAME),
                                    rs.getString(COL_EMAIL),
                                    rs.getString(COL_IMAGEURL),
                                    EAccountStatus.getEnumFromName(rs.getString(COL_STATUS)),
                                    EAccountRole.getEnumFromName(rs.getString(COL_ROLE)));
                            list.add(user);
                        }

                        return list;
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Cannot get list of users");
            } catch (RuntimeException ex) {
                ex.printStackTrace();
            }
            return null;
        });

        return future;
    }

    /**
     * Create User By Id
     *
     * @param userId
     * @return
     */
    @Override
    public UserDTO getUserById(long userId) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ? ";
        try (Connection conn = DBUtils.getConnection()) {
            if (conn == null) {
                throw new SQLException("Cannot get connection");
            }
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, userId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        long id = rs.getLong(COL_ID);
                        String email = rs.getNString(COL_EMAIL);
                        String fullName = rs.getNString(COL_FULLNAME);
                        String nickName = rs.getNString(COL_NICKNAME);
                        String imageUrl = rs.getString(COL_IMAGEURL);
                        EAccountStatus status = EAccountStatus.getEnumFromName(rs.getString(COL_STATUS));
                        EAccountRole role = EAccountRole.getEnumFromName(rs.getString(COL_ROLE));
                        byte age = rs.getByte(COL_AGE);
                        GenderDTO gender = GenderDAO.getInstance().getGenderMap().get(rs.getLong(COL_GENDER_ID));
                        GenderDTO preferenceGender = GenderDAO.getInstance().getGenderMap().get(rs.getLong(COL_GENDER_PREFERENCE_ID));

                        return new UserDTO(id, age, gender, preferenceGender, nickName, fullName, email, imageUrl, status, role);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Cannot get user by id: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public void insertUser(UserDTO user) {
//        String sql = "INSERT INTO " + TABLE_NAME + " ("
//                + COL_FULLNAME + ", "
//                + COL_NICKNAME + ", "
//                + COL_EMAIL + ", "
//                + COL_PASSWORD + ", "
//                + COL_AGE + ", "
//                + COL_STATUS + ", "
//                + COL_ROLE + ", "
//                + COL_GENDER_ID + ", "
//                + COL_GENDER_PREFERENCE_ID + ", "
//                + COL_CREATE_AT + ", "
//                + COL_IMAGEURL
//                + ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";
//
//        try (Connection conn = DBUtils.getConnection()) {
//            try (PreparedStatement ps = conn.prepareStatement(sql)) {
//                ps.setNString(1, user.getFullName());
//                ps.setNString(2, user.getNickName());
//                ps.setNString(3, user.getPassword());
//                ps.setByte(3, user.getAge());
//                
//            }
//        } catch (SQLException ex) {
//
//        }
    }

    @Override
    public void updateUser(UserDTO udto
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteUser(long l
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
