package com.techvortex.vortex.serviceimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techvortex.vortex.entity.Account;
import com.techvortex.vortex.entity.Authority;
import com.techvortex.vortex.entity.Role;
import com.techvortex.vortex.repository.AuthorityDao;
import com.techvortex.vortex.repository.RegisterDao;
import com.techvortex.vortex.service.RegisterService;

@Service
public class RegisterServiceImp implements RegisterService {

    @Autowired
    RegisterDao registerDao;

    @Autowired
    AuthorityDao authorityDao;

    @Override
    public void save(Account account) {
        Authority authority = new Authority();
        Role role = new Role();
        role.setRoleId(1);
        account.setActive(true);
        authority.setAccount(account);
        authority.setRole(role);
        registerDao.save(account);
        authorityDao.save(authority);
    }

    @Override
    public List<Account> CheckEmailUser(String email) {
        return registerDao.CheckEmail(email);
    }

    @Override
    public List<Account> CheckUserName(String username) {
        return registerDao.CheckUserName(username);
    }

}
