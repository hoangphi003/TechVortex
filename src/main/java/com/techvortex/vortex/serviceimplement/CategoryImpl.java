package com.techvortex.vortex.serviceimplement;
import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.techvortex.vortex.entity.Category;
import com.techvortex.vortex.repository.CategoryDAO;
import com.techvortex.vortex.service.CategoryService;
import javax.transaction.Transactional;

@Service
public class CategoryImpl implements CategoryService {
    
    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public List<Category> findAll() {
        // TODO Auto-generated method stub
        return categoryDAO.findAll();
    }

    @Override
    @Transactional
    public Category findById(Integer CategoryId) {
        // TODO Auto-generated method stub
        return categoryDAO.findById(CategoryId).get();
    }

    @Override
    public Category create(Category category) {
        // TODO Auto-generated method stub
        return categoryDAO.save(category);
    }

    @Override
    public Category update(Category category) {
        // TODO Auto-generated method stub
        return categoryDAO.save(category);
    }

    @Override
    public void delete(Category category) {
        // TODO Auto-generated method stub
        categoryDAO.delete(category);
    }
    
}
