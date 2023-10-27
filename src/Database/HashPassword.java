package src.Database;

import java.security.MessageDigest;

public class HashPassword {

    public static String hexPassword(String password){
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }

            return hexString.toString();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
