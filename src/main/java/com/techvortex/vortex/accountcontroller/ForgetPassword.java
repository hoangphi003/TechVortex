package com.techvortex.vortex.accountcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgetPassword {
    @GetMapping("/quenmatkhau")
    public String ForgetPassword() {
        return "forgetpassword";
    }

}
