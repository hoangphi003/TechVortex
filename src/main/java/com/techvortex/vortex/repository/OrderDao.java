package com.techvortex.vortex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techvortex.vortex.entity.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {

    @Query("select o from Order o where o.account.UserName = :name")
    List<Order> findOrderByUsername(@Param("name") String userName);

    @Query("select o from Order o where o.OrderId = :id")
    Order findOrderById(@Param("id") Integer id);

    @Query("select o from Order o where o.account.UserName = :name and o.OrderStatus like :status")
    List<Order> findOrderByStatus(@Param("name") String userName, @Param("status") String keyword);

}
