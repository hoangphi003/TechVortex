package com.techvortex.vortex.service;

import java.util.List;
import com.techvortex.vortex.entity.Brand;

public interface BrandService {

    public List<Brand> findAll();
    
    public Brand findById(Integer BrandId); // tìm kiếm theo id 

   public Brand create(Brand brand); // thêm danh sách

   public Brand update(Brand brand); // sửa danh sách

   public void delete(Brand brand);
    
}
