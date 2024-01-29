package com.techvortex.vortex.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techvortex.vortex.entity.Product;
import com.techvortex.vortex.entity.ProductDetail;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
   
}
