package com.techvortex.vortex.accountcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String HomeLogin() {
        return "login";
    }

}
