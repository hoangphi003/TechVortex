package com.techvortex.vortex.repository;

import com.techvortex.vortex.entity.Account;
import com.techvortex.vortex.entity.Brand;
import com.techvortex.vortex.entity.Review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ReviewsDao extends JpaRepository<Review, Integer>{
    
     @Query("SELECT r FROM Review r WHERE LOWER(r.Rating) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Review> findByReviewNameContaining(@Param("name") String name, Pageable pageable);
}
