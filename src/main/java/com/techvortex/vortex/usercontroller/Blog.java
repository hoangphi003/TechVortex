package com.techvortex.vortex.usercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Blog {
    @GetMapping("/blog")
    public String HomeBlog() {
        return "/blog";
    }

}
