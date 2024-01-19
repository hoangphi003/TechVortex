package com.techvortex.vortex.accountcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChangePassword {

    @GetMapping("/doimatkhau")
    public String OpsPassword() {
        return "changepassword";
    }

}
