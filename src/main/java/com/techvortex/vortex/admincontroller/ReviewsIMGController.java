package com.techvortex.vortex.admincontroller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.techvortex.vortex.entity.Brand;
import com.techvortex.vortex.entity.Discount;
import com.techvortex.vortex.entity.Review;
import com.techvortex.vortex.entity.ReviewImg;
import com.techvortex.vortex.service.ReviewsService;
import com.techvortex.vortex.service.ReviewsimgService;

@Controller
@RequestMapping("/admin")
public class ReviewsIMGController {

    @Autowired
    ReviewsimgService reviewsimgService;

    private static String UPLOAD_DIRECTORY = System.getProperty("user.dir")
            + "/src/main/resources/static/assets/imagesadmin/";

    @GetMapping("/reviewsimg")
    public String showReviewsImgList(Model model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<ReviewImg> pages = reviewsimgService.findAll(pageable);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", p.orElse(0));
        model.addAttribute("reviewsImg", new ReviewImg());
        return "admin/pages/ReviewsImg";
    }

    @PostMapping("/savereviewimg")
    public String createBrand(@ModelAttribute @Validated ReviewImg reviewImg, BindingResult bindingResult,
            @RequestParam("image") MultipartFile image, Model model) {
        if (image != null && !image.isEmpty()) {
            try {
                String fileName = image.getOriginalFilename();
                Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fileName);
                Files.write(fileNameAndPath, image.getBytes());
                reviewImg.setImage(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            model.addAttribute("photo_message", "Photo is mandatory");
            // Thêm brand vào model để hiển thị lại dữ liệu đã nhập
            model.addAttribute("reviewImg", reviewImg);
            // Thêm danh sách brand (pages) vào model để giữ nguyên dữ liệu dưới bảng
            Page<ReviewImg> pages = reviewsimgService.findAll(PageRequest.of(0, 5));
            model.addAttribute("pages", pages);
            model.addAttribute("currentPage", 0);
            return "admin/pages/ReviewsImg";
        }

        if (bindingResult.hasErrors()) {
            // Thêm brand vào model để hiển thị lại dữ liệu đã nhập
            model.addAttribute("reviewImg", reviewImg);
            // Thêm danh sách brand (pages) vào model để giữ nguyên dữ liệu dưới bảng
            Page<ReviewImg> pages = reviewsimgService.findAll(PageRequest.of(0, 5));
            model.addAttribute("pages", pages);
            model.addAttribute("currentPage", 0);
            return "admin/pages/ReviewsImg";
        }

        // Xử lý logic khi không có lỗi
        reviewsimgService.create(reviewImg);

        return "redirect:/admin/reviewsimg";
    }

    @GetMapping("/reviewsimg/edit/{reviewsImgId}")
    public String showUpdateForm(@PathVariable("reviewsImgId") Integer reviewsImgId, Model model) {
        ReviewImg reviewsImg = reviewsimgService.findById(reviewsImgId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reviews image Id:" + reviewsImgId));
        Page<ReviewImg> pages = reviewsimgService.findAll(PageRequest.of(0, 5));
        model.addAttribute("reviewsImg", reviewsImg);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", 0);
        return "admin/pages/ReviewsImg";
    }

    @GetMapping("/reviewsimg/delete/{reviewsImgId}")
    public String deleteReviewsImg(@PathVariable("reviewsImgId") Integer reviewsImgId) {
        ReviewImg reviewsImg = reviewsimgService.findById(reviewsImgId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reviews image Id:" + reviewsImgId));
        reviewsimgService.delete(reviewsImg);
        return "redirect:/admin/reviewsimg";
    }
}
