package com.techvortex.vortex.serviceimplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techvortex.vortex.entity.ProductDetail;
import com.techvortex.vortex.repository.ProductDetailDao;
import com.techvortex.vortex.service.ProductDetailService;

@Service
public class ProductDetailServiceImp implements ProductDetailService {

    @Autowired
    ProductDetailDao productDetailDao;

    @Override
    public ProductDetail findById(Integer id) {
        return productDetailDao.findById(id).get();
    }
    

    @Override
    public boolean existsById(Integer id) {
        return productDetailDao.existsById(id);
    }
}
