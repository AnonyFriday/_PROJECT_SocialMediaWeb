/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group03.loveit.models.account;

/**
 * Define 3 objects only of roles
 *
 * @author duyvu
 */
public enum EAccountStatus {
    // ===========================
    // == No. objects
    // ===========================
    ACTIVE("active"), BLOCKED("deleted"), HIDDEN("blocked");

    // ===========================
    // == Fields
    // ===========================
    private String status;

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

    public static void main(String[] args) {
        System.out.println("Hello");
    }   
}
