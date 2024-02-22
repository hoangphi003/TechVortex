package com.techvortex.vortex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techvortex.vortex.entity.Order;


@Repository
public interface OrderDAO extends JpaRepository<Order,Integer> {
    @Query("SELECT COUNT(o) FROM Order o WHERE MONTH(o.OrderDate) = :month AND YEAR(o.OrderDate) = :year")
    Long countOrdersByMonth(@Param("month") int month, @Param("year") int year);
}
