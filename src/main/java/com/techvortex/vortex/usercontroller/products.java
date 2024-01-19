package com.techvortex.vortex.usercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class products {
    @GetMapping("/products")
    public String products() {
        return "products";
    }
    
}
