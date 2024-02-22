package com.techvortex.vortex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techvortex.vortex.entity.OrderDetail;

@Repository
public interface OrderDetailDAO extends JpaRepository<OrderDetail,Integer> {
     
}
