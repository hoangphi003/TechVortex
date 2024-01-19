package com.techvortex.vortex.admincontroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ColorController {
    @GetMapping("/color")
    public String Color() {
        return "admin/pages/Color";
    }
}
