/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group03.loveit.models.account;

import com.group03.loveit.models.account.EAccountRole;
import com.group03.loveit.models.account.EAccountStatus;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author duyvu
 */
public abstract class AccountDTO implements Serializable {

    // ===========================
    // == Fields
    // ===========================
    private long id;
    private String email;
    private String password;
    private String fullName;
    private String imageUrl;
    private EAccountStatus status;
    private EAccountRole role;
    private LocalDateTime createdAt;

    // ===========================
    // == Constructor
    // ===========================
    /**
     * Default Constructor For JavaBean
     */
    public AccountDTO() {
    }

    /**
     * Constructor for comparing Account
     *
     * @param id
     */
    public AccountDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor for retrieving User without password
     *
     * @param id
     * @param email
     * @param password
     * @param fullName
     * @param imageUrl
     * @param status
     * @param role
     */
    public AccountDTO(long id, String email, String fullName, String imageUrl, EAccountStatus status, EAccountRole role) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.imageUrl = imageUrl;
        this.status = status;
        this.role = role;
    }

    /**
     * Constructor for login
     *
     * @param email
     * @param password
     */
    public AccountDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor for register
     *
     * @param email
     * @param password
     * @param fullName
     */
    public AccountDTO(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.createdAt = LocalDateTime.now();
        this.status = EAccountStatus.ACTIVE;
    }

    // ===========================
    // == Methods
    // ===========================
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    /**
     * For comparing two accounts
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccountDTO other = (AccountDTO) obj;
        return this.id == other.id;
    }

    // ===========================
    // == Getters & Setter &
    // ===========================
    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public EAccountStatus getStatus() {
        return status;
    }

    public void setStatus(EAccountStatus status) {
        this.status = status;
    }

    public EAccountRole getRole() {
        return role;
    }

    public void setRole(EAccountRole role) {
        this.role = role;
    }
}
