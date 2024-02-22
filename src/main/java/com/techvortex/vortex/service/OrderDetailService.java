package com.techvortex.vortex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techvortex.vortex.entity.OrderDetail;

@Service
public interface OrderDetailService {

    public List<OrderDetail> findAll();
    
    public OrderDetail findById(Integer OrderDetailId); // tìm kiếm theo id 

   public OrderDetail create(OrderDetail orderDetail); // thêm danh sách

   public OrderDetail update(OrderDetail orderDetail); // sửa danh sách

   public void delete(OrderDetail orderDetail);
    
   public Float calculateTotalRevenue(); // tổng doanh thu

   public Integer calculateTotalProductsSold();// tổng sản phẩm bán được

   public Integer calculateTotalOrdersSold(); // tổng đơn hàng bán được
}
