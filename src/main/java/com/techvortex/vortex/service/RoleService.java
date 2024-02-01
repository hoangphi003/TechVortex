package com.techvortex.vortex.service;

import java.util.List;

import com.techvortex.vortex.entity.Role;

public interface RoleService {

    void save(Role role);

    List<Role> findAll();

    Role findById(String id);

    void delete(String id);
    
}
