package com.techvortex.vortex.usercontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.techvortex.vortex.entity.Cart;
import com.techvortex.vortex.entity.Order;
import com.techvortex.vortex.service.CartService;
import com.techvortex.vortex.service.OrderService;

@Controller
public class PurchaseOrder {

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/purchaseorder/{id}")
    public String PurchaseOrder(@PathVariable("id") Integer id, Model model) {
        String userName = request.getRemoteUser();
        List<Cart> list = cartService.findAllCart(userName);

        model.addAttribute("findAll", list);
        model.addAttribute("quantity", cartService.displayqty(userName));
        model.addAttribute("PurchaseOrder", orderService.findOrderById(id));
        return "purchaseorder";
    }

}
