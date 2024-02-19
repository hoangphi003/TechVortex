package com.techvortex.vortex.apicontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.techvortex.vortex.entity.Cart;
import com.techvortex.vortex.service.CartService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@RestController
public class CartRestController {

    @Autowired
    CartService cartService;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/getqty")
    public ResponseEntity<Long> getQuantity() {
        return ResponseEntity.ok(cartService.displayqty(request.getRemoteUser()));
    }

}
