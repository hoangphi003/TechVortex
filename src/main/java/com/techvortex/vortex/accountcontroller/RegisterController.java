package com.techvortex.vortex.accountcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public String HomeRegister() {
        return "register";
    }
}
