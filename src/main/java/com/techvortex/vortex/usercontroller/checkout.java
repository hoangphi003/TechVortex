package com.techvortex.vortex.usercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class checkout {
    @GetMapping("/checkout")
    public String Checkout() {
        return "checkout";
    }

}
