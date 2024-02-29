/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group03.loveit.models.user;

import com.group03.loveit.models.account.AccountDTO;
import com.group03.loveit.models.gender.GenderDTO;
import com.group03.loveit.models.account.EAccountRole;
import com.group03.loveit.models.account.EAccountStatus;
import java.io.Serializable;

/**
 *
 * @author duyvu
 */
public class UserDTO extends AccountDTO implements Serializable {

    // ===========================
    // == Fields
    // ===========================
    private byte age;
    private GenderDTO gender;
    private GenderDTO preferenceGender;
    private String nickName;

    // ===========================
    // == Constructors
    // ===========================
    /**
     * Constructor for JavaBean
     */
    public UserDTO() {
    }

    /**
     * Constructor for fetching data
     *
     * @param id
     * @param age
     * @param gender
     * @param preferenceGender
     * @param nickName
     * @param fullName
     * @param email
     * @param imageUrl
     * @param status
     * @param role
     */
    public UserDTO(
            long id,
            byte age,
            GenderDTO gender,
            GenderDTO preferenceGender,
            String nickName,
            String fullName,
            String email,
            String imageUrl,
            EAccountStatus status,
            EAccountRole role) {
        super(id, email, fullName, imageUrl, status, role);
        this.age = age;
        this.gender = gender;
        this.preferenceGender = preferenceGender;
        this.nickName = nickName;
    }

    /**
     * Constructor for register
     *
     * @param age
     * @param gender
     * @param preferenceGender
     * @param email
     * @param password
     * @param fullName
     */
    public UserDTO(String email, String password, String fullName, byte age, GenderDTO gender, GenderDTO preferenceGender) {
        super(email, password, fullName);
        this.age = age;
        this.gender = gender;
        this.preferenceGender = preferenceGender;
    }

    // ===========================
    // == Methods
    // ===========================
    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public GenderDTO getGender() {
        return gender;
    }

    public void setGender(GenderDTO gender) {
        this.gender = gender;
    }

    public GenderDTO getPreferenceGender() {
        return preferenceGender;
    }

    public void setPreferenceGender(GenderDTO preferenceGender) {
        this.preferenceGender = preferenceGender;
    }
}
