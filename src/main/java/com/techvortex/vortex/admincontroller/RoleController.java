package com.techvortex.vortex.admincontroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class RoleController {
    @GetMapping("/role")
    public String Role() {
        return "admin/pages/Role";
    }
}
