package com.techvortex.vortex.admincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techvortex.vortex.entity.Role;
import com.techvortex.vortex.service.RoleService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/role")
    public String FillAllRole(Role role, Model model) {
        model.addAttribute("findAll", roleService.findAll());
        return "admin/pages/Role";
    }

    @PostMapping("/saverole")
    public String SaveRole(Role role, Model model) {
        roleService.save(role);
        return "redirect:/admin/role";
    }

    @GetMapping("/editrole/{id}")
    public String EditRole(@PathVariable("id") String id, Role role, Model model) {
        model.addAttribute("role", roleService.findById(id));
        FillAllRole(role, model);
        return "admin/pages/Role";
    }

    @GetMapping("/deleterole/{id}")
    public String DeleteRole(@PathVariable("id") String id, Role role, Model model) {
        roleService.delete(id);
        FillAllRole(role, model);
        return "admin/pages/Role";
    }

}
