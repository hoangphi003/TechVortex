package com.techvortex.vortex.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techvortex.vortex.entity.Category;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Integer> {
    
}
