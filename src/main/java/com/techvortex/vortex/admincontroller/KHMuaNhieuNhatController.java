package com.techvortex.vortex.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KHMuaNhieuNhatController {
    @RequestMapping("/khachhang")
    public String KHMuaNhieuNhat() {
        return "admin/KHMuaNhieuNhat";
    } 
}
