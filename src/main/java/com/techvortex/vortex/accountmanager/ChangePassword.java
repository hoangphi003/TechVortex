package com.techvortex.vortex.accountmanager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techvortex.vortex.entity.Account;
import com.techvortex.vortex.repository.AccountDao;

@Controller
public class ChangePassword {
    @Autowired
    AccountDao accountsDao;

    @GetMapping("/changepassword")
    public String OpsPassword() {
        return "changepassword";
    }

    @PostMapping("/changepassword")
    public String changePasswordSuccess(Model model, RedirectAttributes m, @RequestParam("password") String password,
            @RequestParam("newpassword") String newpassword, @RequestParam("confirmpassword") String confirmpassword,
            HttpServletRequest request) {
        String user = request.getRemoteUser();
        if (user != null) {
            if (newpassword.equals(confirmpassword)) {
                Account acc = accountsDao.findByUsername(user);
                if (acc != null) {
                    if (password.equals(acc.getPassword())) {
                        acc.setPassword(newpassword);
                        accountsDao.save(acc);
                        model.addAttribute("mess", "Đổi mật khẩu thành công!");
                    } else {
                        m.addFlashAttribute("mess", "Password không đúng!");
                        return "redirect:/changepassword";
                    }
                }
            } else {
                m.addFlashAttribute("mess", "New Password và Confirm password phải giống nhau!");
                return "redirect:/changepassword";
            }

        } else {
            m.addFlashAttribute("mess", "Vui lòng đăng nhập!");
            return "redirect:/changepassword";
        }
        return "changepassword";
    }

}
