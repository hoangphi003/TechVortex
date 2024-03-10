package com.techvortex.vortex.accountmanager;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techvortex.vortex.entity.Account;
import com.techvortex.vortex.repository.AccountDao;
import com.techvortex.vortex.service.LoginService;

@Controller
public class ForgetPassword {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JavaMailSender javaMailSender; 

    @Autowired
    private AccountDao accountDao;

    @RequestMapping("/forgetpassword")
    public String showForgetPasswordPage() {
        return "forgetpassword";
    }

    @PostMapping("/forgetpassword")
    public String processForgetPassword(@RequestParam("email") String email, Model model) {
        // Assuming you have a method in LoginService to find an account by email
        Account user = loginService.findbyemail(email);

        if (user != null) {
            // Generate a random password or token for the reset
            String newPassword = generateRandomPassword();
            
            // Update the user's password
            user.setPassword(newPassword);
            accountDao.save(user);

            // Send an email with the new password
            sendResetEmail(user.getEmail(), newPassword);

            model.addAttribute("successMessage", "Reset link sent to your email");
        } else {
            model.addAttribute("errorMessage", "Email not found");
        }

        return "forgetpassword";
    }

    private void sendResetEmail(String userEmail, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
    
        // Set the subject of the email
        message.setSubject("TechVortex - Password Reset");
    
        // Set the text of the email
        String emailText = "Kính gửi người dùng TechVortex,\n\n" +
                "Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu của bạn.\n" +
                "mật khẩu mới của bạn là: " + newPassword + "\n\n" +
                "Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng liên hệ với nhóm hỗ trợ của chúng tôi.\n\n" +
                "Cảm ơn,\n" +
                "Nhóm TechVortex";
    
        message.setText(emailText);
    
        // Send the email using JavaMailSender
        javaMailSender.send(message);
    }

    private String generateRandomPassword() {
        // Implement your logic to generate a random password
        // For simplicity, this example generates a random 8-character password
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder newPassword = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = (int) (Math.random() * characters.length());
            newPassword.append(characters.charAt(index));
        }
        return newPassword.toString();
    }
}
