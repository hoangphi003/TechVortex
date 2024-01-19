package com.techvortex.vortex.usercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Cart {
    @GetMapping("/cart")
    public String Cart() {
        return "/cart";
    }

}
