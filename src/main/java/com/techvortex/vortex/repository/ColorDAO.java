package com.techvortex.vortex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techvortex.vortex.entity.Color;

@Repository
public interface ColorDAO extends JpaRepository<Color, Integer> {
    
}
