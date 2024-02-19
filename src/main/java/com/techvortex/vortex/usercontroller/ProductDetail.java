package com.techvortex.vortex.usercontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.techvortex.vortex.entity.Cart;
import com.techvortex.vortex.service.CartService;
import com.techvortex.vortex.service.ProductDetailService;

@Controller
public class ProductDetail {

    @Autowired
    ProductDetailService productDetailService;

    @Autowired
    CartService cartService;

    @GetMapping("/productdetail/{id}")
    public String HomeProductDetail(Model model, @PathVariable("id") Integer id, HttpServletRequest request) {
        List<Cart> list = cartService.findAllCart(request.getRemoteUser());
        String userName = request.getRemoteUser();

        model.addAttribute("findAll", list);
        model.addAttribute("quantity", cartService.displayqty(userName));
        model.addAttribute("detail", productDetailService.findById(id));
        return "product-detail";
    }

}
