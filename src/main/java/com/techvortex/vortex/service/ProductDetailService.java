package com.techvortex.vortex.service;

import com.techvortex.vortex.entity.Product;
import com.techvortex.vortex.entity.ProductDetail;
import com.techvortex.vortex.repository.ProductDetailDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
public interface ProductDetailService {
       
    // @Autowired
    // private ProductDetail productDetailRepository;
   
    // public ProductDetail findByProductId(Integer productId) {
    //     return productDetailRepository.findByProduct_ProductId(productId);
    // }
    // ProductDetail findByProduct(Product product);
    
    public List<ProductDetail> findAll(); // in ra lưu vào danh sách

   public ProductDetail create(ProductDetail productDetail); // thêm danh sách

   public ProductDetail update(ProductDetail productDetail); // sửa danh sách

   public void delete(ProductDetail productDetail);


}
