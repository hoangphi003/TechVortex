package com.techvortex.vortex.accountmanager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.techvortex.vortex.entity.Account;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String HomeLogin(Account account) {
        return "login";
    }

    @PostMapping("/UserLogin")
    public String UserLoginHome() {
        return "login";
    }

    @GetMapping("/login/success")
    public String SuccessLogin(Model model) {
        model.addAttribute("messageSuccess", "bạn đã đăng nhập thành công");
        return "login";
    }

    @GetMapping("/login/fail")
    public String FailLogin(Model model) {
        model.addAttribute("messageFail", "kiểm tra lại tên đăng nhập và mật khẩu");
        return "login";
    }

}