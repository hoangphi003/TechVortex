package com.techvortex.vortex.admincontroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ReviewsController {
    @GetMapping("/reviews")
    public String Reviews() {
        return "admin/pages/Reviews";
    }
}
