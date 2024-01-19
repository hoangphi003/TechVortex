package com.techvortex.vortex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techvortex.vortex.entity.ProductDetail;

public interface ProductDetailDao extends JpaRepository<ProductDetail, Integer> {

}
