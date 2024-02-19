package com.techvortex.vortex.usercontroller;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.techvortex.vortex.entity.Cart;
import com.techvortex.vortex.service.CartService;

@Controller
public class checkout {

    @Autowired
    CartService cartService;

    @Autowired
    HttpServletRequest request;

    List<Cart> list = new ArrayList<>();

    @GetMapping("/checkout")
    public String Checkout(Model model) {
        if (list.isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("listCheckout", list);
        String userName = request.getRemoteUser();
        List<Cart> list = cartService.findAllCart(userName);

        model.addAttribute("findAll", list);
        model.addAttribute("quantity", cartService.displayqty(userName));
        return "checkout";
    }

    @GetMapping("/checkout/{id}")
    public String CheckoutAdd(@PathVariable("id") Integer id, Model model) {
        Cart cart = cartService.findById(id);
        for (Cart x : list) {
            if (x.getCartId().equals(cart.getCartId())) {
                return "redirect:/cart";
            }
        }
        list.add(cart);
        return "redirect:/checkout";
    }

    @GetMapping("/removecheckout/{id}")
    public String RemoveCheckout(@PathVariable("id") Integer id, Model model) {
        Cart cart = cartService.findById(id);
        try {
            for (Cart x : list) {
                if (x.getCartId().equals(cart.getCartId())) {
                    list.remove(x);
                }
            }
        } catch (Exception e) {
            return "redirect:/cart";
        }
        return "redirect:/checkout";
    }
}
