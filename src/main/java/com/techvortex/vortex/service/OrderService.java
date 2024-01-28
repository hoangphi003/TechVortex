package com.techvortex.vortex.service;

import java.util.List;

import com.techvortex.vortex.entity.Order;

public interface OrderService {
    List<Order> findOrderByUsername(String userName);

    Order findOrderById(Integer id);

    void save(Order order);

    List<Order> findOrderByStatus(String userName, String keyword);

}
