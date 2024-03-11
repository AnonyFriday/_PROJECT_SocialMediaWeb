/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group03.loveit.models.account;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Define 2 objects only of roles
 *
 *
 *
 * @author duyvu
 */
public enum EAccountStatus {
    // ===========================
    // == No. objects
    // ===========================
    /**
     * - Active: the current active account
     *
     * - Disable: disabled account will be blocked to login from the system
     */
    ACTIVE("Active"), DISABLE("Disable");

    // ===========================
    // == Fields
    // ===========================
    private final String status;

    // ===========================
    // == Constructor
    // ===========================
    /**
     * By default the enum constructor is private
     *
     * @param role: shorten form of enum object
     */
    private EAccountStatus(String status) {
        this.status = status;
    }

    // ===========================
    // == Methods
    // ===========================
    /**
     * Return the shorten form of status
     *
     * @return string status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the enum object based on the string form
     *
     * @param name
     * @return
     */
    public static EAccountStatus getEnumFromName(String name) {
        return Arrays.stream(EAccountStatus.values()).filter(new Predicate<EAccountStatus>() {
            @Override
            public boolean test(EAccountStatus t) {
                return t.getStatus().equalsIgnoreCase(name);
            }
        }).findFirst().get();
    }
}
