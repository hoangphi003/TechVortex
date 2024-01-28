package com.techvortex.vortex.usercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Compare {

    @GetMapping("/compare")
    public String HomeCompare() {
        return "compare";
    }

}
