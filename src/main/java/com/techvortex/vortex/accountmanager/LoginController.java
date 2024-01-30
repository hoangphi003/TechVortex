package com.techvortex.vortex.accountmanager;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.techvortex.vortex.configuration.SecurityConfig;
import com.techvortex.vortex.entity.Account;
import com.techvortex.vortex.service.RegisterService;

@Controller
public class LoginController {

    @Autowired
    SecurityConfig config;

    @Autowired
    RegisterService registerService;

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

    @GetMapping("/loginsocial/auth")
    public String SuccessLoginSocial(OAuth2AuthenticationToken oauth2, Model model) {
        String userName = oauth2.getPrincipal().getAttribute("name");
        String email = oauth2.getPrincipal().getAttribute("email");
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String password = RandomStringUtils.random(15, characters);

        Account FindAccount = registerService.findByUserNameG(userName);

        Account account = new Account();
        if (FindAccount == null) {
            account.setUserName(userName);
            account.setEmail(email);
            account.setPassword(password);
            registerService.save(account);
        }
        return "redirect:/index";
    }

    @GetMapping("/login/fail")
    public String FailLogin(Model model) {
        model.addAttribute("messageFail", "kiểm tra lại tên đăng nhập và mật khẩu");
        return "login";
    }

}