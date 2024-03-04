/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group03.loveit.models.account;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Define 2 objects only of roles
 */
public enum EAccountRole {

    // ===========================
    // == No. objects
    // ===========================
    ADMIN("Admin"), USER("User");

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

    /**
     * Get the enum object based on the string form
     *
     * @param name
     * @return
     */
    public static EAccountRole getEnumFromName(String name) {
        return Arrays.stream(EAccountRole.values()).filter(new Predicate<EAccountRole>() {
            @Override
            public boolean test(EAccountRole t) {
                return t.getRole().equalsIgnoreCase(name);
            }
        }).findFirst().get();
    }
}
