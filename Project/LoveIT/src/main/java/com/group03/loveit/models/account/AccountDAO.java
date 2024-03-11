/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group03.loveit.models.account;

import com.group03.loveit.models.user.UserDAO;
import com.group03.loveit.utilities.CryptoUtils;
import com.group03.loveit.utilities.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyvu
 */
public class AccountDAO {

    // ===========================
    // == Fields
    // ===========================
    private final String COL_EMAIL = "Email";
    private final String COL_PASSWORD = "Password";
    private final String COL_FULLNAME = "FullName";
    private final String COL_ROLE = "Role";
    private final String COL_STATUS = "Status";

    // ===========================
    // == Methods
    // ===========================
    /**
     * Login using username and password
     *
     * @param email
     * @param password
     * @return an account model
     */
    public AccountDTO login(String email, String password) {
        String sql = "SELECT u.Email, u.Password, u.Fullname, u.Role, u.Status\n"
                + "FROM [User] as u\n"
                + "WHERE u.Email = ?";

        // Fetching data
        try (Connection conn = DBUtils.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {

                        // Checking hash password
                        byte[] storedPassword = rs.getString(COL_PASSWORD).getBytes();
                        boolean isVerified = CryptoUtils.verify(password, storedPassword);

                        if (isVerified) {
                            AccountDTO account = new AccountDTO(
                                    rs.getString(COL_EMAIL),
                                    rs.getString(COL_PASSWORD),
                                    rs.getString(COL_FULLNAME),
                                    EAccountRole.getEnumFromName(rs.getString(COL_ROLE)),
                                    EAccountStatus.getEnumFromName(rs.getString(COL_STATUS))
                            );
                            return account;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    /**
     * Checking if the account is admin
     *
     * @param account
     * @return
     */
    public boolean isAdmin(AccountDTO account) {
        return account.getRole().equals(EAccountRole.ADMIN);
    }

    /**
     * Checking if the account is user
     *
     * @param account
     * @return
     */
    public boolean isUser(AccountDTO account) {
        return account.getRole().equals(EAccountRole.USER);
    }
}
