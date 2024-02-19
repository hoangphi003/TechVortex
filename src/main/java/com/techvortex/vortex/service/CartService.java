package com.techvortex.vortex.service;

import java.util.List;

import com.techvortex.vortex.entity.Cart;

public interface CartService {

    void save(Cart cart);

    List<Cart> findAll();

    Cart findCartByUsernameAndProductDetailId(String UserName, Integer ProductDetailId);

    List<Cart> findAllCart(String userName);

    void deleteById(Integer cartId);

    Cart findById(Integer CartId);

    Cart findByCartId(String userName, Integer id);

    void clearAllCart(String userName);

    Long displayqty(String userName);

}
