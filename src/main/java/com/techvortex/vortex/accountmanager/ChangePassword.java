package com.techvortex.vortex.accountmanager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChangePassword {

    @GetMapping("/changepassword")
    public String OpsPassword() {
        return "changepassword";
    }

}
