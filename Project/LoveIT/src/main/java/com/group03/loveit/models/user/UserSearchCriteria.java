/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group03.loveit.models.user;

/**
 * A class for retrieving searching keyword
 *
 * @author duyvu
 */
public final class UserSearchCriteria {

    // ===========================
    // == Fields
    // ===========================
    private String nickName;
    private String fullName;

    // ===========================
    // == Constructors
    // ===========================
    public UserSearchCriteria(String nickName, String fullName) {
        this.nickName = nickName;
        this.fullName = fullName;
    }

    // ===========================
    // == Methods
    // ===========================
    public String getNickName() {
        return (nickName == null || nickName.trim().isEmpty()) ? "" : nickName;
    }

    public String getFullname() {
        return (fullName == null || fullName.trim().isEmpty()) ? "" : fullName;
    }
}
