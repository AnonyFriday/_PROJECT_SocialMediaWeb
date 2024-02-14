/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group03.loveit.models.user;

import com.group03.loveit.models.IDAO;

/**
 *
 * @author duyvu
 */
public class UserDAO implements IDAO<UserDTO> {

    // ===========================
    // == Standard CRUD
    // ===========================
    @Override
    public boolean insert(UserDTO instance) {
        return false;
    }

    @Override
    public UserDTO get() {
        return null;
    }

    @Override
    public boolean update(UserDTO t) {
        return false;
    }

    @Override
    public boolean delete(UserDTO t) {
        return false;
    }
}
