package com.techvortex.vortex.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.techvortex.vortex.service.ProductDetailService;

@Controller
public class ProductDetail {

    @Autowired
    ProductDetailService productDetailService;

    @GetMapping("/productdetail/{id}")
    public String HomeProductDetail(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("detail", productDetailService.findById(id));
        return "/product-detail";
    }

}
