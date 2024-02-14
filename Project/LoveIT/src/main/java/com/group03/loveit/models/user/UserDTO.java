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
    private GenderDTO preference_gender;
//    private Feed feed;

    // ===========================
    // == Constructors
    // ===========================
    public UserDTO(long id, String email, String password, String fullName, String imageUrl, EAccountStatus status, EAccountRole role, byte age, GenderDTO gender, GenderDTO preference_gender) {
        super(id, email, password, fullName, imageUrl, status, role);
        this.age = age;
        this.gender = gender;
        this.preference_gender = preference_gender;
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

    public GenderDTO getGender() {
        return gender;
    }

    public void setGender(GenderDTO gender) {
        this.gender = gender;
    }

    public GenderDTO getPreference_gender() {
        return preference_gender;
    }

    public void setPreference_gender(GenderDTO preference_gender) {
        this.preference_gender = preference_gender;
    }
}
