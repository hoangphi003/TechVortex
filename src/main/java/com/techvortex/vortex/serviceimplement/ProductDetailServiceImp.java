package com.techvortex.vortex.serviceimplement;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techvortex.vortex.entity.Product;
import com.techvortex.vortex.entity.ProductDetail;
import com.techvortex.vortex.repository.ProductDetailDao;
import com.techvortex.vortex.service.ProductDetailService;

@Service
public class ProductDetailServiceImp implements ProductDetailService {

    @Autowired
    ProductDetailDao productDetailDao;

//     @Override
// public ProductDetail findById(Integer id) {
//     Optional<ProductDetail> optionalProductDetail = productDetailDao.findById(id);

//     if (optionalProductDetail.isPresent()) {
//         return optionalProductDetail.get();
//     } else {
//         // Xử lý trường hợp không tìm thấy
//         throw new EntityNotFoundException("Không tìm thấy ProductDetail với ID: " + id);
//     }
// }

    @Override
    public List<ProductDetail> findAll() {

        return productDetailDao.findAll();
    }

    @Override
    public ProductDetail create(ProductDetail productDetail) {

        return productDetailDao.save(productDetail);
    }

    @Override
    public ProductDetail update(ProductDetail productDetail) {

        return productDetailDao.save(productDetail);
    }

    @Override
    public void delete(ProductDetail productDetail) {

        productDetailDao.delete(productDetail);
    }

}
