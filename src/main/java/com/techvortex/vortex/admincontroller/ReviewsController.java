package com.techvortex.vortex.admincontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techvortex.vortex.entity.Brand;
import com.techvortex.vortex.entity.Discount;
import com.techvortex.vortex.entity.Review;
import com.techvortex.vortex.entity.ReviewImg;
import com.techvortex.vortex.service.DiscountService;
import com.techvortex.vortex.service.ReviewsService;

@Controller
@RequestMapping("/admin")
public class ReviewsController {
    @Autowired
    ReviewsService reviewsService;

    @GetMapping("/reviews")
    public String showReviewsList(Model model) {
        model.addAttribute("allreviews", reviewsService.findAll());
        model.addAttribute("review", new Review());
        return "admin/pages/Reviews";
    }

    @GetMapping("/reviews/edit/{reviewId}")
    public String showUpdateForm(@PathVariable("reviewId") Integer reviewId, Model model) {
        // Lấy review cần sửa
        Review review = reviewsService.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid review Id:" + reviewId));

        // Lấy danh sách reviews để giữ nguyên dữ liệu dưới bảng
        Page<Review> pages = reviewsService.findAll(PageRequest.of(0, 5));

        // Thêm review và danh sách reviews vào model
        model.addAttribute("review", review);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", 0);

        return "admin/pages/Reviews";
    }
}
