package com.techvortex.vortex.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import com.techvortex.vortex.entity.Brand;
import com.techvortex.vortex.repository.BrandDao;
import com.techvortex.vortex.service.BrandService;

@Service
public class BrandServiceImp implements BrandService {

    @Autowired
    BrandDao brandDao;

    @Override
    public List<Brand> findAll() {
        return brandDao.findAll();
    }

    @Override
    @Transactional
    public Brand findById(Integer BrandId) {
        return brandDao.findById(BrandId).get();
    }

    @Override
    public Brand create(Brand brand) {
        return brandDao.save(brand);
    }

    @Override
    public Brand update(Brand brand) {
        return brandDao.save(brand);
    }

    @Override
    public void delete(Brand brand) {
        brandDao.delete(brand);
    }
}