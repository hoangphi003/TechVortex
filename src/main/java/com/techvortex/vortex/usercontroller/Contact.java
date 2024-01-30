package com.techvortex.vortex.usercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Contact {
    @GetMapping("/contact")
    public String HomeContact() {
        return "contact";
    }

}
