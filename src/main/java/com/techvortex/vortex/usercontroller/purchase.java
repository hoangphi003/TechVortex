package com.techvortex.vortex.usercontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.techvortex.vortex.entity.Order;
import com.techvortex.vortex.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class purchase {

    @Autowired
    HttpServletRequest request;

    @Autowired
    OrderService orderService;

    @GetMapping("/purchase")
    public String PurchaseAllList(Model model) {
        String UserName = request.getRemoteUser();
        model.addAttribute("order", orderService.findOrderByUsername(UserName));
        // Tìm hóa đơn hủy
        model.addAttribute("orderCancel", orderService.findOrderByStatus(UserName, "%Đã hủy%"));
        // Tìm hóa Chờ thanh toán
        model.addAttribute("orderWaiting", orderService.findOrderByStatus(UserName, "%Chờ thanh toán%"));
        // Tìm hóa đơn vận chuyển
        model.addAttribute("orderShipping", orderService.findOrderByStatus(UserName, "%Vận chuyển%"));
        // Tìm hóa đơn chờ giao hàng
        model.addAttribute("orderDelivery", orderService.findOrderByStatus(UserName, "%Chờ giao hàng%"));
        // Tìm hóa đơn hoàn thành
        model.addAttribute("orderFinish", orderService.findOrderByStatus(UserName, "%Hoàn thành%"));
        return "purchase";
    }

    @GetMapping("/cancelOrder/{id}")
    public String setCancelOrder(@PathVariable("id") Integer id, Model model) {
        PurchaseAllList(model);
        Order order = orderService.findOrderById(id);
        if (order != null) {
            order.setOrderStatus("Đã hủy");
            orderService.save(order);
        }
        return "redirect:/purchase";
    }

    @GetMapping("/OrderReceived/{id}")
    public String setOrderReceived(@PathVariable("id") Integer id, Model model) {
        PurchaseAllList(model);
        Order order = orderService.findOrderById(id);
        if (order != null) {
            order.setOrderStatus("Hoàn thành");
            orderService.save(order);
        }
        return "redirect:/purchase";
    }

}
