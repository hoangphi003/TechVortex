package com.techvortex.vortex.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techvortex.vortex.entity.Material;

@Repository
public interface MaterialDAO extends JpaRepository<Material, Integer> {
    
}
