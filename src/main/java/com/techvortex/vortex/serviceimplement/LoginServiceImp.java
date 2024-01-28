package com.techvortex.vortex.serviceimplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techvortex.vortex.entity.Account;
import com.techvortex.vortex.repository.LoginDao;
import com.techvortex.vortex.service.LoginService;

@Service
public class LoginServiceImp implements LoginService {
    @Autowired
    LoginDao loginDao;

    @Override
    public Account findById(String username) {
        return loginDao.isFindUserNameById(username);
    }

}
