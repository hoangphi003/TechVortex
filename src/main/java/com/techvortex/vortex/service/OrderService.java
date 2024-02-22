package com.techvortex.vortex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techvortex.vortex.entity.Order;
import com.techvortex.vortex.entity.OrderDetail;

@Service
public interface OrderService {

    public List<Order> findAll();
    
    public Order findById(Integer OrderId); // tìm kiếm theo id 

   public Order create(Order order); // thêm danh sách

   public Order update(Order order); // sửa danh sách

   public void delete(Order order);
    
   public Long countOrdersByMonth(int month, int year); // thống kê biểu đồ cột
  
}
