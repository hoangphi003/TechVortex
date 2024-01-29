package com.techvortex.vortex.accountmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.techvortex.vortex.configuration.SecurityConfig;
import com.techvortex.vortex.entity.Account;

@Controller
public class LoginController {

    @Autowired
    SecurityConfig config;

    @GetMapping("/login")
    public String HomeLogin(Account account) {
        return "login";
    }

    @PostMapping("/UserLogin")
    public String UserLoginHome() {
        return "login";
    }

    @GetMapping("/login/success")
    public String SuccessLogin(OAuth2AuthenticationToken oauth2, Model model) {
        config.LoginFormOAuth2(oauth2);
        model.addAttribute("messageSuccess", "bạn đã đăng nhập thành công");
        return "login";
    }

    @GetMapping("/login/fail")
    public String FailLogin(Model model) {
        model.addAttribute("messageFail", "kiểm tra lại tên đăng nhập và mật khẩu");
        return "login";
    }

}