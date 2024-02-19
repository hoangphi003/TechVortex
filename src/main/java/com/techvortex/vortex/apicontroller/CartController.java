package com.techvortex.vortex.apicontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.techvortex.vortex.entity.Account;
import com.techvortex.vortex.entity.Cart;
import com.techvortex.vortex.entity.ProductDetail;
import com.techvortex.vortex.service.CartService;
import com.techvortex.vortex.service.ProductDetailService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    @Autowired
    ProductDetailService detailService;

    @Autowired
    CartService cartService;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/cart")
    public String Cart(Model model) {
        String userName = request.getRemoteUser();
        List<Cart> list = cartService.findAllCart(userName);
        float total = 0.0f;
        for (Cart c : list) {
            Float sum = c.getQuantity() * c.getProductDetails().getProduct().getPrice();
            total += sum;
        }

        model.addAttribute("quantity", cartService.displayqty(userName));
        model.addAttribute("total", total);
        model.addAttribute("findAll", list);
        return "/cart";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") Integer ProductDetailId,
            @RequestParam(name = "quantity", defaultValue = "1") Integer quantity, Model model) {
        String userName = request.getRemoteUser();
        Cart existingCart = cartService.findCartByUsernameAndProductDetailId(userName, ProductDetailId);

        if (existingCart != null) {
            // Lấy số lượng trong kho
            Integer inventoryQty = existingCart.getProductDetails().getInventoryQuantity();

            if (quantity < inventoryQty || existingCart.getQuantity() < inventoryQty) {

                existingCart.setQuantity(existingCart.getQuantity() + quantity);

                if (inventoryQty < existingCart.getQuantity()) {
                    existingCart.setQuantity(inventoryQty);
                }
            } else {
                existingCart.setQuantity(inventoryQty);
            }

            cartService.save(existingCart);
        } else {
            ProductDetail detail = new ProductDetail();
            detail.setProductDetailId(ProductDetailId);

            Account account = new Account();
            account.setUserName(userName);

            Cart cart = new Cart();
            cart.setProductDetails(detail);
            cart.setAccount(account);
            cart.setQuantity(quantity);

            cartService.save(cart);
        }

        return "redirect:/product-detail/" + ProductDetailId;
    }

    @GetMapping("/removeProduct/{id}")
    public String removeProductFromCart(@PathVariable("id") Integer cartId, Model model) {
        cartService.deleteById(cartId);
        return "redirect:/cart";
    }

    @GetMapping("/removeProductAll")
    public String removeProductAllFromCart(Model model) {
        String userName = request.getRemoteUser();
        cartService.clearAllCart(userName);
        return "redirect:/cart";
    }

    @GetMapping("/cart/{id}")
    public String SetQuantityCart(@PathVariable("id") Integer id,
            @RequestParam(name = "qty", defaultValue = "1") Integer qty, Model model) {
        Cart existingCart = cartService.findById(id);
        existingCart.setQuantity(qty);

        cartService.save(existingCart);
        return "redirect:/cart";
    }

}
