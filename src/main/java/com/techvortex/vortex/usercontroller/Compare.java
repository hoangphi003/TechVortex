package com.techvortex.vortex.usercontroller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.techvortex.vortex.entity.Product;
import com.techvortex.vortex.entity.ProductDetail;
import com.techvortex.vortex.repository.ProductDetailDao;

@Controller
public class Compare {

    @Autowired
    ProductDetailDao detailDao;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/compare/{id}")
    public String SaveCompare(@PathVariable("id") Integer id, Model model) {

        ProductDetail detail = detailDao.findById(id).get();
        List<ProductDetail> ListCompare = new ArrayList<>();
        ListCompare.add(detail);
        
        model.addAttribute("ProductCompare", ListCompare);
        return "compare";
    }
}
