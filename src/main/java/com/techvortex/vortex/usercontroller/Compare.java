package com.techvortex.vortex.usercontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.techvortex.vortex.entity.Cart;
import com.techvortex.vortex.entity.Product;
import com.techvortex.vortex.entity.ProductDetail;
import com.techvortex.vortex.repository.ProductDetailDao;
import com.techvortex.vortex.service.CartService;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Compare {

    @Autowired
    ProductDetailDao detailDao;

    @Autowired
    HttpServletRequest request;

    @Autowired
    CartService cartService;

    List<ProductDetail> list = new ArrayList<>();

    @GetMapping("/compare")
    public String Compare(Model model) {
        String userName = request.getRemoteUser();
        List<Cart> listCart = cartService.findAllCart(userName);

        model.addAttribute("ProductCompare", list);
        model.addAttribute("findAll", listCart);
        model.addAttribute("quantity", cartService.displayqty(userName));
        return "compare";
    }

    @GetMapping("/compare/{id}")
    public String SaveCompare(@PathVariable("id") Integer id, Model model) {
        ProductDetail detail = detailDao.findById(id).get();
        for (ProductDetail x : list) {
            if (x.getProductDetailId().equals(detail.getProductDetailId())) {
                return "redirect:/compare";
            }
        }
        if (list.size() < 3) {
            list.add(detail);
        }

        model.addAttribute("ProductCompare", list);
        return "compare";
    }

    @GetMapping("/remove/{id}")
    public String RemoveById(@PathVariable("id") Integer id, Model model) {
        ProductDetail detail = detailDao.findById(id).get();
        try {
            for (ProductDetail x : list) {
                if (x.getProductDetailId().equals(detail.getProductDetailId())) {
                    list.remove(x);
                }
            }
        } catch (ConcurrentModificationException e) {
            return "redirect:/compare";
        }

        return "redirect:/compare";
    }

}
