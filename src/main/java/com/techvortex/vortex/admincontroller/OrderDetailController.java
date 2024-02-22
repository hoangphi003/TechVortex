package com.techvortex.vortex.admincontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techvortex.vortex.service.OrderService;
import com.techvortex.vortex.service.OrderDetailService;

@Controller
@RequestMapping("/admin")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    OrderService orderService;

    @GetMapping("/orderdetail")
    public String OrderDetail(Model model) {
        return "admin/pages/OrderDetail";
    }

    @GetMapping("")
    public String totalSum(Model model) {
        // Lấy tổng doanh thu và đặt nó vào model để hiển thị trên giao diện người dùng
        Float totalRevenue = orderDetailService.calculateTotalRevenue();
        model.addAttribute("totalRevenue", totalRevenue);

        // Lấy tổng số lượng sản phẩm đã bán và đặt nó vào model để hiển thị trên giao
        // diện người dùng
        Integer totalProductsSold = orderDetailService.calculateTotalProductsSold();
        model.addAttribute("totalProductsSold", totalProductsSold);

        // Lấy số lượng đơn hàng bán được và đặt nó vào model để hiển thị trên giao diện người dùng
        Integer totalOrdersSold = orderDetailService.calculateTotalOrdersSold();
        model.addAttribute("totalOrdersSold", totalOrdersSold);

        // Lấy số lượng đơn hàng cho mỗi tháng
        Long januaryCount = orderService.countOrdersByMonth(1, 2024);
        System.out.println("January Count: " + januaryCount);
        // ... lặp lại cho các tháng khác ...

        model.addAttribute("januaryCount", januaryCount);
        // ... thêm các thuộc tính khác cho các tháng khác ...

        return "admin/index";
    }

}
