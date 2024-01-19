package com.techvortex.vortex.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.techvortex.vortex.service.BrandService;

@Controller
public class home {

    @Autowired
    BrandService brandService;

    @GetMapping({ "/home", "/index", "", "/" })
    public String MyHome(Model model) {
        model.addAttribute("findAllBrand", brandService.findAll());
        return "index";
    }

}
