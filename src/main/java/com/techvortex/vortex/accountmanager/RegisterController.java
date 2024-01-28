package com.techvortex.vortex.accountmanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.techvortex.vortex.entity.Account;
import com.techvortex.vortex.service.MailService;
import com.techvortex.vortex.service.RegisterService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Controller
public class RegisterController {

    @Autowired
    JavaMailSender sender;

    private @Autowired MailService mailService;

    @Autowired
    RegisterService registerService;

    @GetMapping("/register")
    public String HomeRegister(Account account) {
        return "register";
    }

    @PostMapping("/AddMember")
    public String RegisterMemberHome(Account account, Model model) {
        List<Account> list = registerService.CheckEmailUser(account.getEmail());
        for (Account x : list) {
            if (account.getEmail().equals(x.getEmail())) {
                model.addAttribute("message", "Email đã sử dụng");
                return "register";
            }
        }
        // check ursename
        List<Account> ListUserName = registerService.CheckUserName(account.getUserName());
        for (Account x : ListUserName) {
            if (account.getUserName().equals(x.getUserName())) {
                model.addAttribute("messageInfo", "Tên đã sử dụng");
                return "register";
            }
        }

        registerService.save(account);

        try {// Tạo message
            MimeMessage message = sender.createMimeMessage();
            // Sử dụng Helper để thiết lập các thông tin cần thiết cho message
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom("Admin");
            helper.setTo(account.getEmail());
            helper.setSubject(
                    "Chào bạn " + account.getUserName() + ", bạn đã đăng ký tài khoản thành công tại TechVortex");
            helper.setText("<div class=\"container\">\r\n" + //
                    "        <div class=\"content\" style=\"background-color: #eff2f1; margin: auto; width: 760px;\r\n"
                    + //
                    "        border-top: 5px solid #3b5d50; border-left: 1px solid gainsboro;\r\n" + //
                    "        border-bottom: 1px solid gainsboro;border-right: 1px solid gainsboro;\">\r\n" + //
                    "            <h1 style=\"margin-bottom: 50px; text-align: center;\">Chào mừng đến với TechVortex</h1>\r\n"
                    + //
                    "            <p style=\"margin-bottom: 25px; padding: 0 30px; font-size: large;\">Xin chào "
                    + account.getUserName() + " !</p>\r\n"
                    + //
                    "            <p style=\"margin-bottom: 65px; padding: 0 30px;font-size: large;\">Cảm ơn bạn đã đăng ký TechVortex. Chúng tôi thực sự hạnh\r\n"
                    + //
                    "                phúc khi có bạn!<br>\r\n" + //
                    "                nhấp\r\n" + //
                    "                vào liên kết bên dưới để đăng nhập vào tài khoản của bạn:\r\n" + //
                    "            </p>\r\n" + //
                    "            <a href=\"http://localhost:8080/login\" style=\"position: absolute;\r\n" + //
                    "    left: 50%;\r\n" + //
                    "    transform: translateX(-50%);\r\n"
                    + //
                    "          padding: 15px; background-color: #3b5d50; margin:0 14rem;\r\n"
                    + //
                    "            color: #ffff; text-decoration: none; border-radius: 7px;font-size: large;\r\n" + //
                    "            \">Đăng nhập vào tài khoản của\r\n" + //
                    "                bạn</a>\r\n" + //
                    "            <p style=\"margin-top: 80px; padding: 0 30px;font-size: large;\">Trân trọng,</p>\r\n"
                    + //
                    "            <p style=\"margin-top: 25px;margin-bottom: 30px; padding: 0 30px;font-size: large;\">TechVortex Team</p>\r\n"
                    + //
                    "        </div>\r\n" + //
                    "    </div>", true);
            mailService.add(message);
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        model.addAttribute("success", "bạn đã đăng ký thành công.");
        model.addAttribute("account", new Account());
        return "register";
    }

}