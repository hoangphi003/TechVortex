package com.techvortex.vortex.admincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techvortex.vortex.Passwordencoder.PasswordEncoderMH;
import com.techvortex.vortex.entity.Account;
import com.techvortex.vortex.service.AccountService;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin")
@MultipartConfig
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/account")
    public String account(Model model) {
        // Tạo một đối tượng Account để binding với form
        model.addAttribute("account", new Account());
        // Sử dụng attribute name khác nhau để tránh trùng lặp
        // Mã hóa mật khẩu để hiển thị trong ô input
        String rawPassword = "password";
        String displayedPassword = PasswordEncoderMH.encode(rawPassword);
        model.addAttribute("displayedPassword", displayedPassword);
        // Mã hóa mật khẩu để hiển thị trong ô input
        String encodedPassword = PasswordEncoderMH.encode("password");
        model.addAttribute("encodedPassword", encodedPassword);

        // Đặt giá trị mặc định để hiển thị trong ô input
        model.addAttribute("displayedPassword", encodedPassword);

        model.addAttribute("allAccounts", accountService.findAll());

        return "admin/pages/Account";
    }

    // edit account
    @GetMapping("/editaccount/{Username}")
    public String showUpdateForm(@PathVariable("Username") String Username, Model model) {
        // Lấy account cần sửa
        Account account = accountService.findById(Username);
        // Kiểm tra xem account có tồn tại hay không
        if (account == null) {
            // Xử lý tùy thuộc vào yêu cầu của ứng dụng khi account không tồn tại
            return "redirect:/admin/accounts"; // Chuyển hướng đến trang danh sách accounts
        }
        // Thêm trạng thái "edit" vào model
        model.addAttribute("isEditing", true);
        String encodedPassword = PasswordEncoderMH.encode("password");
        model.addAttribute("encodedPassword", encodedPassword);

        // Đặt giá trị mặc định để hiển thị trong ô input
        model.addAttribute("displayedPassword", encodedPassword);
        // Thêm account vào model
        model.addAttribute("account", account);
        // Lấy danh sách accounts để giữ nguyên dữ liệu dưới bảng (nếu cần)
        model.addAttribute("allAccounts", accountService.findAll());
        // Trả về fragment của form
        return "admin/pages/Account";
    }

    // update dữ liệu account
    // Trong AccountController.java
    private static String UPLOAD_DIRECTORY = System.getProperty("user.dir")
            + "/src/main/resources/static/assets/images/products/";

    @PostMapping("/saveAccount")
    public String saveAccount(@ModelAttribute("account") Account account, Model model) {
        // Xử lý cập nhật tài khoản
        // Thêm account vào model
        // Kiểm tra xem người dùng có nhập mật khẩu mới hay không
        if (account.getPassword() == null || account.getPassword().isEmpty()) {
            // Nếu không có mật khẩu mới, giữ nguyên mật khẩu hiện tại
            Account existingAccount = accountService.findById(account.getUserName());
            if (existingAccount != null) {
                account.setPassword(existingAccount.getPassword());
            }
        } else {
            // Nếu có mật khẩu mới, mã hóa và gán giá trị mới cho mật khẩu
            String encodedPassword = PasswordEncoderMH.encode(account.getPassword());
            account.setPassword(encodedPassword);
        }
        // mã hoas dữ liệu trên thanh input sau khi cập nhật dữ liệu
        String rawPassword = "password";
        String displayedPassword = PasswordEncoderMH.encode(rawPassword);
        model.addAttribute("displayedPassword", displayedPassword);
        // Tính toán encodedPassword (mình giả sử bạn có một PasswordEncoder)
        String encodedPassword = PasswordEncoderMH.encode(account.getPassword());
        model.addAttribute("account", account);
        model.addAttribute("allAccounts", accountService.findAll());
        model.addAttribute("encodedPassword", encodedPassword);
        // Lấy danh sách accounts để giữ nguyên dữ liệu dưới bảng (nếu cần)
        accountService.update(account);
        model.addAttribute("successMessage", "Cập nhật tài khoản thành công!");

        // Chuyển hướng hoặc thực hiện các thao tác khác tùy thuộc vào yêu cầu của ứng
        // dụng
        return "admin/pages/Account"; // Chuyển hướng đến trang danh sách accounts
    }

}
