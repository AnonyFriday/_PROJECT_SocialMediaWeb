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
    // ===========================
    // == Methods
    // ===========================
}
