/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group03.loveit.utilities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author duyvu
 */
public final class DataProcessingUtils {

    /**
     * Parse literal string into the LocalDate object
     *
     * @param date
     * @param pattern
     * @return
     */
    public static LocalDate parseLocalDate(String date, String pattern) {
        if (isNullOrEmpty(date)) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date, formatter);
    }

    /**
     * Convert date of birth to age
     *
     * @param dob
     * @return age
     */
    public static byte convertDateToAge(LocalDate dob) {
        return (byte) LocalDate.now().minusYears(dob.getYear()).getYear();
    }

    /**
     * Checking if retype password is matching with password or not
     *
     * @param password
     * @param retypedPassword
     * @return true if valid
     */
    public static boolean isRetypedPasswordValid(String password, String retypedPassword) {
        return isPasswordValid(password) && isPasswordValid(password) && password.equals(retypedPassword);
    }

    /**
     * Check password is valid or not
     *
     * @param password
     * @return true if valid or else false
     */
    public static boolean isPasswordValid(String password) {
        // at least 1 character
        // at least 1 digit
        // length minimum is 8
        // contain digits, characters, !,@,#,$ only
        return !isNullOrEmpty(password) && password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z!@#$\\d]{8,}$");
    }

    /**
     * Check if date of birth is valid
     *
     * @param dob
     * @return true if valid or else false
     */
    public static boolean isDateOfBirthValid(LocalDate dob) {
        // at least 1 character
        // at least 1 digit
        // length minimum is 8
        // contain digits, characters, !,@,#,$ only
        return !isNullOrEmpty(dob) && (LocalDate.now().getYear() - dob.getYear()) >= 18;
    }

    /**
     * Check Nickname is valid or not
     *
     * @param nickName
     * @param min
     * @param max
     * @return true if valid or else false
     */
    public static boolean isNickNameValid(String nickName, int min, int max) {
        return !isNullOrEmpty(nickName)
                && nickName.matches("^[a-zA-Z ]+$")
                && (nickName.length() >= min)
                && (nickName.length() <= max);
    }

    /**
     * Check fullName is valid or not
     *
     * @param fullName
     * @param min
     * @param max
     * @return true if valid or else false
     */
    public static boolean isFullNameValid(String fullName, int min, int max) {
        return !isNullOrEmpty(fullName)
                && fullName.matches("^[a-zA-Z ]+$")
                && (fullName.length() >= min)
                && (fullName.length() <= max);
    }

    /**
     * Checking if email is valid or not
     *
     * @param email
     * @param min
     * @param max
     * @return true if valid or else false
     */
    public static boolean isEmailValid(String email, int min, int max) {
        String regex = "^[a-z0-9]+@([a-z]+\\.)+[a-z]{2,4}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return !isNullOrEmpty(email)
                && matcher.matches()
                && (email.length() >= min)
                && (email.length() <= max);
    }

    /**
     * Checking if object is null or not (empty with string)
     *
     * @param <T>
     * @param value
     * @return true if null or empty with string
     */
    public static <T> boolean isNullOrEmpty(T value) {
        if (null == value) {
            return true;
        }

        if (value instanceof String) {
            return ((String) value).trim().isEmpty();
        }

        return false;
    }
}
