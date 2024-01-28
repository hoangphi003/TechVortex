package com.techvortex.vortex.accountmanager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForgetPassword {
    @GetMapping("/forgetpassword")
    public String ForgetPassword() {
        return "forgetpassword";
    }

}
