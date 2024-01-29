package com.techvortex.vortex.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import com.techvortex.vortex.entity.Category;
import com.techvortex.vortex.entity.Product;
import com.techvortex.vortex.entity.ProductDetail;
import com.techvortex.vortex.service.CategoryService;
import com.techvortex.vortex.service.ProductDetailService;
import com.techvortex.vortex.service.ProductService;
import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;

@Controller
@RequestMapping("/admin")
@MultipartConfig
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductDetailService productdetailService;

    @Autowired
    CategoryService categoryService;

    private static String UPLOAD_DIRECTORY = "src/main/resources/static/assets/images/products/";

    @GetMapping("/product")
    public String Product(@ModelAttribute("product") Product product, Model model) {
        //  model.addAttribute("product", new Product());
        model.addAttribute("allProducts", productService.findAll());
        model.addAttribute("allCategorys", categoryService.findAll());

        return "admin/pages/Product";
    }

    @PostMapping("/saveProduct")
    public String saveAccount(@Validated @ModelAttribute("product") Product product,
            BindingResult bindingResult, Category category,
            RedirectAttributes redirectAttributes, Model model) {
              
                if (bindingResult.hasErrors()) {
            model.addAttribute("allProducts", productService.findAll());
            model.addAttribute("allCategorys", categoryService.findAll());
            return "admin/pages/Product";
        }
      
        String imageName = product.getImage().replaceAll(",", "");
        product.setImage(imageName);
        productService.create(product);
        categoryService.create(category);

        redirectAttributes.addFlashAttribute("successMessage", "Lưu sản phẩm thành công!");
        redirectAttributes.addFlashAttribute("operation", "add"); // Thêm thông điệp động

        return "redirect:/admin/product"; // Chuyển hướng đến trang danh sách accounts
    }
    
    // edit product
    @GetMapping("editproduct/{ProductId}")
    public String EditProduct(@PathVariable("ProductId") Integer ProductId, Model model) {
        Product product = productService.findById(ProductId);
        model.addAttribute("product", product);
        model.addAttribute("allProducts", productService.findAll());
        model.addAttribute("allCategorys", categoryService.findAll());

        return "admin/pages/Product";
    }

    // editproductdetail
    @GetMapping("/editproductdetai/{ProductId}")
    public String editProductdetail(@PathVariable("ProductId") Integer productId, Model model) {
        Product product = productService.findById(productId);
    
        if (product == null) {
            model.addAttribute("errorMessage", "Không tìm thấy thông tin sản phẩm");
            return "redirect:/admin/product";
        }
    
        model.addAttribute("product", product);
        model.addAttribute("allProducts", productService.findAll()); // Nếu không sử dụng thì có thể loại bỏ
        model.addAttribute("productDetail", new ProductDetail());
        return "admin/pages/ProductDetails";
    }
    
    
 
    @GetMapping("/delete/{ProductId}")
    public String deleteProduct(@PathVariable("ProductId") Integer ProductId, RedirectAttributes redirectAttributes) {
        try {
            Product product = productService.findById(ProductId);
            productService.delete(product);
            // Đặt thông báo thành công vào redirectAttributes
            redirectAttributes.addFlashAttribute("deletesuccessMessage", "Xóa thành công!");
        } catch (Exception e) {
            // Đặt thông báo lỗi vào redirectAttributes nếu có lỗi
            redirectAttributes.addFlashAttribute("deleteerrorMessage", "Xóa thất bại: " + e.getMessage());
        }
        // Chuyển hướng người dùng đến trang hiển thị danh sách loại sản phẩm hoặc trang
        // chính của trang quản trị
        return "redirect:/admin/product";
    }

}
