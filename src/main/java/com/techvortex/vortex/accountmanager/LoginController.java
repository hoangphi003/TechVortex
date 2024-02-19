package com.techvortex.vortex.accountmanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @Autowired
    HttpServletRequest request;

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
        return "redirect:/index";
    }

    @GetMapping("/loginsocial/auth")
    public String SuccessLoginSocial(OAuth2AuthenticationToken oauth2, Model model, HttpSession session) {
        String userName = oauth2.getPrincipal().getName();
        String fullName = oauth2.getPrincipal().getAttribute("name");
        String email = oauth2.getPrincipal().getAttribute("email");
        String picture = oauth2.getPrincipal().getAttribute("picture");
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String password = RandomStringUtils.random(8, characters);

        Account FindAccount = registerService.findByUserNameG(userName);

        Account account = new Account();
        if (FindAccount == null) {
            account.setUserName(userName);
            account.setEmail(email);
            account.setFullName(fullName);
            account.setPassword(password);
            account.setAvatar(picture);
            registerService.save(account);
        }
        return "redirect:/index";
    }

    @GetMapping("/login/fail")
    public String FailLogin(Model model) {
        model.addAttribute("messageFail", "kiểm tra tên đăng nhập và mật khẩu của bạn");
        return "login";
    }

}