package com.techvortex.vortex.admincontroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techvortex.vortex.service.OrderService;

@Controller
@RequestMapping("/admin")
public class OrderController {
 
    @Autowired
    OrderService orderService;

    @GetMapping("/order")
    public String Order() {
        return "admin/pages/Order";
    }

    @GetMapping("/orderform")
    public String OrderForm() {
        return "admin/pages/OrderForm";
    }

  
}
