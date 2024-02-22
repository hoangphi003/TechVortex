package com.techvortex.vortex.serviceimplement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import com.techvortex.vortex.entity.Order;
import com.techvortex.vortex.entity.OrderDetail;
import com.techvortex.vortex.repository.OrderDAO;
import com.techvortex.vortex.service.BrandService;
import com.techvortex.vortex.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO dao;

    @Override
    public List<Order> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public Order findById(Integer OrderId) {
        return dao.findById(OrderId).get();
    }

    @Override
    public Order create(Order order) {
        return dao.save(order);
    }

    @Override
    public Order update(Order order) {
        return dao.save(order);
    }

    @Override
    public void delete(Order order) {
        dao.delete(order);
    }

    @Override
    public Long countOrdersByMonth(int month, int year) {
        return dao.countOrdersByMonth(month, year); // thống kê đơn hàng theo tháng biểu đồ cột
    }

    
}