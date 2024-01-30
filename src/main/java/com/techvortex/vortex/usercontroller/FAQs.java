package com.techvortex.vortex.usercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FAQs {
    @GetMapping("/faqs")
    public String FAQs() {
        return "FAQs";
    }

}
