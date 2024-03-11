/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group03.loveit.utilities;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duyvu
 */
public final class CryptoUtils {

    private static int saltSize = 16;

    /**
     * Decode into byte arrays from encrypted string
     *
     * @param encryptedString
     * @return
     */
    private final static byte[] decode(String encryptedString) {
        return Base64.getDecoder().decode(encryptedString);
    }

    /**
     * Verify encrypted and input string
     *
     * @param enteredString
     * @param encryptedString
     * @return
     */
    public final static boolean verify(String enteredString, String encryptedString) {
        try {
            // Decode the stored password from Base64
            byte[] storedBytes = decode(encryptedString);

            // Extract the salt from the encrypted String
            byte[] salt = new byte[saltSize]; // Assuming a 16-byte salt
            System.arraycopy(storedBytes, 0, salt, 0, salt.length);

            // Combine entered String and stored salt
            byte[] combined = new byte[enteredString.length() + salt.length];
            System.arraycopy(enteredString.getBytes(), 0, combined, 0, enteredString.length());
            System.arraycopy(salt, 0, combined, enteredString.length(), salt.length);

            // Hash the combined value using SHA-512
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = messageDigest.digest(combined);

            // Compare the hashed value with the stored hash
            for (int i = 0; i < hashedBytes.length; i++) {
                if (hashedBytes[i] != storedBytes[salt.length + i]) {
                    return false; // Passwords do not match
                }
            }

            return true; // Passwords match
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return false;
        }
    }

    /**
     * Encoding the data string using the SHA-256
     *
     * @param rawString
     * @return
     */
    private final static String encode(String rawString) {
        try {
            // Generate a random salt
            byte[] salt = generateSalt();

            // Combine password and salt
            byte[] combined = new byte[rawString.length() + salt.length];
            System.arraycopy(rawString.getBytes(), 0, combined, 0, rawString.length());
            System.arraycopy(salt, 0, combined, rawString.length(), salt.length);

            // Hash the combined value using SHA-512
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = messageDigest.digest(combined);

            // Combine the salt and hashed password for storage
            byte[] storedPassword = new byte[salt.length + hashedBytes.length];
            System.arraycopy(salt, 0, storedPassword, 0, salt.length);
            System.arraycopy(hashedBytes, 0, storedPassword, salt.length, hashedBytes.length);

            // Encode the combined value to Base64 for storage
            return Base64.getEncoder().encodeToString(storedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // Handle the exception appropriatel
        }
        return null;
    }

    /**
     * Generate the Salt for SHA-512
     *
     * @return
     */
    private static byte[] generateSalt() {
        byte[] salt = new byte[saltSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }
}
