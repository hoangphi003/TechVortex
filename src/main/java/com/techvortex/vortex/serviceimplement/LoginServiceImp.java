package com.techvortex.vortex.serviceimplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techvortex.vortex.entity.Account;
import com.techvortex.vortex.repository.LoginDao;
import com.techvortex.vortex.service.LoginService;

@Service
public class LoginServiceImp implements LoginService {
    @Autowired
    LoginDao loginDao;

    @Override
    @Transactional
    public Account findById(String UserName) {
        return loginDao.findById(UserName).get();
    }

    @Override
    public Account findbyemail(String email) {
        return loginDao.isFindUserNameByIdEmail(email);
    }

}
