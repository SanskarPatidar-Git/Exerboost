package com.arbutus.exerboost.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    /**
     * This method validates whether input email is valid or not.
     */
    public static int isValidEmail(String email) {
        if (isStringEmpty(email)) {
            return 1;
        } else if (!isValidEmailFormat(email)) {
            return 2;
        }
        return 0;
    }
    public static boolean isValidEmailFormat(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * A password is considered valid if all the following constraints are satisfied :
     * <p>
     * It contains at least 8 characters and at most 20 characters.
     * It contains at least one digit.
     * It contains at least one upper case alphabet.
     * It contains at least one lower case alphabet.
     * It contains at least one special character which includes !@#$%&*()-+=^.
     * It dose not contain any white space.
     * <p>
     * Example :
     * “Geeks@portal20”    = true      // This password satisfies all constraints mentioned above.
     * “Geeksforgeeks”     = false     // It contains upper case and lower case alphabet but doesn’t contains any digits, and special characters.
     * “Geeks@ portal9”    = false     // It contains upper case alphabet, lower case alphabet, special characters, digits along with white space which is not valid.
     * <p>
     * Explain : Regular Expression.
     * <p>
     * regex = “^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8, 20}$”
     * <p>
     * where :
     * ^ represents starting character of the string.
     * (?=.*[0-9]) represents a digit must occur at least once.
     * (?=.*[a-z]) represents a lower case alphabet must occur at least once.
     * (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
     * (?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
     * (?=\\S+$) white spaces don’t allowed in the entire string.
     * .{8, 20} represents at least 8 characters and at most 20 characters.
     * $ represents the end of the string.
     * <p>
     * https://www.techiedelight.com/validate-password-java/
     */
    public static int isValidPassword(String password) {

        /* represents an upper case alphabet that must occur at least once. */
        boolean hasUppercase = !password.matches(".*[A-Z].*");

        /* represents a lower case alphabet must occur at least once. */
        boolean hasLowercase = !password.matches(".*[a-z].*");

        /* represents a digit must occur at least once. */
        boolean hasNumber = !password.matches(".*\\d+.*");

        /* represents a special character that must occur at least once. */
        boolean hasSpecialCharacter = !password.matches(".*[!@#$%&*()-+=^].*");

        /* white spaces don’t allowed in the entire string. */
        boolean hasWhiteSpaces = password.matches(".*[ ].*");

        if (isStringEmpty(password)) {
            return 1;
        } else if (password.length() < 8) {
            return 2;
        } else if (password.length() > 30) {
            return 3;
        } else if (hasUppercase) {
            return 4;
        } else if (hasLowercase) {
            return 5;
        } else if (hasNumber) {
            return 6;
        } else if (hasSpecialCharacter) {
            return 7;
        } else if (hasWhiteSpaces) {
            return 8;
        }
        return 0;
    }

    public static int isValidConfirmPassword(String password, String confirmPassword) {
        if (isStringEmpty(password)) {
            return 1;
        } else if (isStringEmpty(confirmPassword)) {
            return 2;
        } else if (confirmPassword.length() < 8) {
            return 3;
        } else if (!confirmPassword.equals(password)) {
            return 4;
        }
        return 0;
    }

    public static boolean isStringEmpty(String string) {
        return (string == null || string.trim().length() == 0);
    }
}

