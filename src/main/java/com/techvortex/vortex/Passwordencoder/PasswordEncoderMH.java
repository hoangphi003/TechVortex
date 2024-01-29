package com.techvortex.vortex.Passwordencoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoderMH  {
    public static String encode(String password) {
        try {
            // Kiểm tra xem password có là null không
            if (password == null) {
                throw new IllegalArgumentException("Password cannot be null");
            }

            // Sử dụng SHA-256 để mã hóa mật khẩu
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedBytes = digest.digest(password.getBytes());

            // Chuyển đổi byte array thành dạng hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Thử nghiệm với một password không phải là null
        String rawPassword = "password";
        String encodedPassword = encode(rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);
    }
}
