package com.techvortex.vortex.admincontroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @GetMapping("/order")
    public String Order() {
        return "admin/pages/Order";
    }
}
