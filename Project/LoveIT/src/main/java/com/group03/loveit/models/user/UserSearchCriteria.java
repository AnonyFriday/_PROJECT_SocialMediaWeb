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
    private String fullname;

    // ===========================
    // == Constructors
    // ===========================
    public UserSearchCriteria(String nickName, String fullname) {
        this.nickName = nickName;
        this.fullname = fullname;
    }

    // ===========================
    // == Methods
    // ===========================
    public String getNickName() {
        return nickName;
    }

    public String getFullname() {
        return fullname;
    }
}
