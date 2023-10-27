/**
 * The HashPassword class provides a method for hashing passwords using the SHA-256 algorithm.
 */
package src.Database;

import java.security.MessageDigest;

public class HashPassword {

    /**
     * Hashes a password using the SHA-256 algorithm and returns the hashed password as a hexadecimal string.
     *
     * @param password The password to be hashed.
     * @return A hexadecimal representation of the hashed password or null in case of an error.
     */
    public static String hexPassword(String password) {
        try {
            // Create a MessageDigest object with the SHA-256 algorithm
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");

            // Compute the hash of the password
            byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

            // Convert the hash to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }

            return hexString.toString();
        } catch (Exception e) {
            // Handle any exceptions and print an error message
            System.out.println(e);
            return null;
        }
    }
}