package com.techvortex.vortex.service;

import com.techvortex.vortex.entity.ProductDetail;

public interface ProductDetailService {

    ProductDetail findById(Integer id);

    boolean existsById(Integer id);

    
}
