package com.techvortex.vortex.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class OrderDetailController {
    @GetMapping("/orderdetail")
    public String OrderDetail() {
        return "admin/pages/OrderDetail";
    }
}
