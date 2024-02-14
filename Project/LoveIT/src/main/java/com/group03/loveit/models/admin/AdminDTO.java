/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group03.loveit.models.admin;

import com.group03.loveit.models.account.AccountDTO;
import com.group03.loveit.models.account.EAccountRole;
import com.group03.loveit.models.account.EAccountStatus;
import java.io.Serializable;

/**
 *
 * @author duyvu
 */
public class AdminDTO extends AccountDTO implements Serializable {

    // ===========================
    // == Constructors
    // ===========================
    public AdminDTO(long id, String email, String password, String fullName, String imageUrl, EAccountRole role) {
        super(id, email, password, fullName, imageUrl, role);
    }

}
