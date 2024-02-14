/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group03.loveit.models.account;

/**
 * Define 2 objects only of roles
 */
public enum EAccountRole {

    // ===========================
    // == No. objects
    // ===========================
    ADMIN("admin"), USER("user");

    // ===========================
    // == Fields
    // ===========================
    private String role;

    // ===========================
    // == Constructor
    // ===========================
    /**
     * By default the enum constructor is private
     *
     * @param role: shorten form of enum object
     */
    EAccountRole(String role) {
        this.role = role;
    }

    // ===========================
    // == Methods
    // ===========================
    /**
     * Get the shorten form of enum object
     *
     * @return string role
     */
    public String getRole() {
        return role;
    }
}
