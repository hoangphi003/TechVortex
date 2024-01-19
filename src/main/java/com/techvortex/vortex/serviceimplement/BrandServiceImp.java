package com.techvortex.vortex.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}