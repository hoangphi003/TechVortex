package com.techvortex.vortex.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.techvortex.vortex.entity.Order;
import com.techvortex.vortex.service.OrderService;

@Controller
public class PurchaseOrder {

    @Autowired
    OrderService orderService;

    @GetMapping("/purchaseorder/{id}")
    public String PurchaseOrder(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("PurchaseOrder", orderService.findOrderById(id));
        return "purchaseorder";
    }

}
