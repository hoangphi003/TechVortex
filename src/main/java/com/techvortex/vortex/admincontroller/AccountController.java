package com.techvortex.vortex.admincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techvortex.vortex.entity.Account;
import com.techvortex.vortex.service.AccountService;
import java.util.List;
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
        model.addAttribute("allAccounts", accountService.findAll());

        return "admin/pages/Account";
    }

    @GetMapping("/activeform")
    public String OrderForm(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("allAccounts", accountService.findAll());
        return "admin/pages/ActiveForm";
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

        // Thêm account vào model
        model.addAttribute("account", account);
        // Lấy danh sách accounts để giữ nguyên dữ liệu dưới bảng (nếu cần)
        model.addAttribute("allAccounts", accountService.findAll());
        // Trả về fragment của form
        return "admin/pages/activeform";
    }

    // update dữ liệu account
    // Trong AccountController.java
    private static String UPLOAD_DIRECTORY = System.getProperty("user.dir")
            + "/src/main/resources/static/assets/images/products/";

            @PostMapping("/saveAccount")
            public String saveAccount(@ModelAttribute("account") Account account, Model model,
                    @RequestParam("status") String status) {
            
                try {
                    // Xác nhận dữ liệu đầu vào
                    if (account.getUserName() == null) {
                        throw new IllegalArgumentException("Username không được null");
                    }
            
                    // Kiểm tra nếu tài khoản đã tồn tại trong cơ sở dữ liệu
                    Account existingAccount = accountService.findById(account.getUserName());
                    if (existingAccount != null) {
                        // Thiết lập trạng thái từ tham số status
                        account.setActive("true".equalsIgnoreCase(status));
            
                        // Gọi phương thức updateStatus để cập nhật trạng thái tài khoản
                        accountService.updateStatus(account.getUserName(), account.getActive());
            
                        // Cập nhật model và in ra thông điệp thành công
                        model.addAttribute("account", account);
                        model.addAttribute("allAccounts", accountService.findAll());
                        model.addAttribute("successMessage", "Cập nhật trạng thái tài khoản thành công!");
                        System.out.println("Received POST request for /saveAccount");
                    } else {
                        throw new IllegalArgumentException("Không tìm thấy tài khoản với username: " + account.getUserName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // Xử lý ngoại lệ nếu có
                    model.addAttribute("errorMessage", "Lỗi khi cập nhật trạng thái tài khoản: " + e.getMessage());
                }
            
                return "admin/pages/Account";
            }
            
            

    // @RequestMapping("/updatestatus/{Username}")
    // public String saveAccount(@PathVariable("Username") String Username, @ModelAttribute("account") Account account,
    //         Model model) {
    //     Account accountt = accountService.findById(Username);
    //     if (accountt == null) {
    //         System.out.println("Không tìm thấy username!");
    //     } else {
    //         accountt.setActive(false);
    //         accountService.create(accountt); // Sử dụng phương thức update để cập nhật tài khoản hiện tại
    //     }
    //     model.addAttribute("allAccounts", accountService.findAll()); // Giữ nguyên dữ liệu dưới bảng
    //     return "admin/pages/Account";
    // }
//     @PostMapping("/updatestatus/{Username}")
// public ResponseEntity<String> updateAccountStatus(
//         @PathVariable("Username") String Username,
//         @RequestParam("isActive") boolean isActive) {
    
//     // Kiểm tra xác thực và quyền truy cập nếu cần

//     try {
//         // Gọi phương thức updateStatus của service để cập nhật trạng thái
//         accountService.updateStatus(Username, isActive);

//         return new ResponseEntity<>("Cập nhật trạng thái thành công!", HttpStatus.OK);
//     } catch (Exception e) {
//         e.printStackTrace();
//         return new ResponseEntity<>("Lỗi khi cập nhật trạng thái.", HttpStatus.INTERNAL_SERVER_ERROR);
//     }
// }

    

}
