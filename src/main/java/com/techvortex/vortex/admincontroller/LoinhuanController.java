package com.techvortex.vortex.admincontroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoinhuanController {
    @RequestMapping("/loinhuan")
    public String Loinhuan() {
        return "admin/Loinhuan";
    }
}
