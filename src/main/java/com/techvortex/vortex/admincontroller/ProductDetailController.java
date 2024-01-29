package com.techvortex.vortex.admincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.techvortex.vortex.service.BrandService;
import com.techvortex.vortex.service.ColorService;
import com.techvortex.vortex.service.MaterialService;
import com.techvortex.vortex.service.ProductDetailService;
import com.techvortex.vortex.service.ProductService;
import com.techvortex.vortex.entity.Brand;
import com.techvortex.vortex.entity.Color;
import com.techvortex.vortex.entity.Material;
import com.techvortex.vortex.entity.Product;
import com.techvortex.vortex.entity.ProductDetail;

@Controller
@RequestMapping("/admin")
public class ProductDetailController {

    @Autowired
    ProductDetailService productdeService;

    @Autowired
    ProductService productService;

    @Autowired
    MaterialService materialService;

    @Autowired
    ColorService colorService;

    @Autowired
    BrandService brandService;

    @GetMapping("/productdetail")
    public String Product(@ModelAttribute("productDetail") ProductDetail productDetail,
            BindingResult bindingResult,
            Color color, Brand brand, Material material, Product product, Model model) {
        model.addAttribute("allProductdetail", productdeService.findAll());
        model.addAttribute("allProducts", productService.findAll());
        model.addAttribute("allBrand", brandService.findAll());
        model.addAttribute("allColor", colorService.findAll());
        model.addAttribute("allMaterial", materialService.findAll());
        return "admin/pages/ProductDetails";
    }

    @GetMapping("/editproductdetail/{ProductId}")
    public String editProductDetail(@PathVariable("ProductId") Integer productId, Model model) {
        Product product = productService.findById(productId);

        if (product == null) {
            model.addAttribute("errorMessage", "Không tìm thấy thông tin sản phẩm");
            return "redirect:/admin/product";
        }
        model.addAttribute("allProductdetail", productdeService.findAll());
        model.addAttribute("allProducts", productService.findAll());
        model.addAttribute("allBrand", brandService.findAll());
        model.addAttribute("allColor", colorService.findAll());
        model.addAttribute("allMaterial", materialService.findAll());

        // Tạo một ProductDetail và đặt Product vào đó
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);

        // Truyền thông tin của sản phẩm sang form chỉnh sửa của ProductDetail
        model.addAttribute("productDetail", productDetail);

        return "admin/pages/ProductDetails";
    }

    // lưu sản phẩm
    @PostMapping("/saveProductdetail")
    public String saveAccount(@Validated @ModelAttribute("productDetail") Product product,
            BindingResult bindingResult, ProductDetail produtcDetail, Color color, Brand brand, Material material,
            RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("allProductdetail", productdeService.findAll());
            model.addAttribute("allProducts", productService.findAll());
            model.addAttribute("allBrand", brandService.findAll());
            model.addAttribute("allColor", colorService.findAll());
            model.addAttribute("allMaterial", materialService.findAll());
            // model.addAttribute("allCategorys", categoryService.findAll());
            return "admin/pages/ProductDetails";
        }

        productdeService.create(produtcDetail);
        productService.create(product);
        colorService.create(color);
        brandService.create(brand);
        materialService.create(material);

        redirectAttributes.addFlashAttribute("successMessage", "Lưu sản phẩm thành công!");
        redirectAttributes.addFlashAttribute("operation", "add"); // Thêm thông điệp động

        return "redirect:/admin/pages/ProductDetails"; // Chuyển hướng đến trang danh sách accounts
    }

}
