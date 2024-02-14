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
    private long genderId;
    private long preferenceGenderId;
    private String nickName;
//    private Feed feed;

    // ===========================
    // == Constructors
    // ===========================
    public UserDTO(long id, String email, String fullName,
            String imageUrl, EAccountStatus status, EAccountRole role,
            byte age, long genderId, long preferenceGenderId, String nickName) {
        super(id, email, fullName, imageUrl, status, role);
        this.age = age;
        this.genderId = genderId;
        this.preferenceGenderId = preferenceGenderId;
        this.nickName = nickName;
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
}
