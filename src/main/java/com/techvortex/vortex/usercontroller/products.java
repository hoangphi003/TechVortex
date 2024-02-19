package com.techvortex.vortex.usercontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.techvortex.vortex.entity.Cart;
import com.techvortex.vortex.service.CartService;

@Controller
public class products {
    @Autowired
    HttpServletRequest request;

    @Autowired
    CartService cartService;

    @GetMapping("/products")
    public String products(Model model) {
        String userName = request.getRemoteUser();
        List<Cart> list = cartService.findAllCart(userName);

        model.addAttribute("findAll", list);
        model.addAttribute("quantity", cartService.displayqty(userName));
        return "/products";
    }

}
