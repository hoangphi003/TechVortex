package com.techvortex.vortex.usercontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.techvortex.vortex.entity.Cart;
import com.techvortex.vortex.service.BrandService;
import com.techvortex.vortex.service.CartService;

@Controller
public class home {

    @Autowired
    BrandService brandService;

    @Autowired
    CartService cartService;

    @GetMapping({ "/home", "/index", "", "/" })
    public String MyHome(Model model, HttpServletRequest request) {
        String userName = request.getRemoteUser();
        List<Cart> list = cartService.findAllCart(userName);

        model.addAttribute("findAll", list);
        model.addAttribute("quantity", cartService.displayqty(userName));
        model.addAttribute("findAllBrand", brandService.findAll());
        return "index";
    }

}
