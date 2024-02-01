package com.techvortex.vortex.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techvortex.vortex.entity.Role;
import com.techvortex.vortex.repository.RoleDao;
import com.techvortex.vortex.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id).get();
    }

    @Override
    public void delete(String id) {
        roleDao.deleteById(id);
    }
}
