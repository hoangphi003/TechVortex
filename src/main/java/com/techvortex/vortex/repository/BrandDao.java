package com.techvortex.vortex.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techvortex.vortex.entity.Brand;

public interface BrandDao extends JpaRepository<Brand, Integer> {
    @Query("SELECT b FROM Brand b WHERE LOWER(b.BrandName) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Brand> findByBrandNameContaining(@Param("name") String name, Pageable pageable);

   
}
