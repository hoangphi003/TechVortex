package com.techvortex.vortex.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techvortex.vortex.entity.Brand;
import com.techvortex.vortex.entity.Discount;

public interface DiscountDao extends JpaRepository<Discount, Integer>{
      @Query("SELECT d FROM Discount d WHERE LOWER(d.DiscountName) LIKE LOWER(CONCAT('%', :name, '%'))")
      Page<Discount> findByDiscountNameContaining(@Param(value="name") String name, Pageable pageable);
    }

