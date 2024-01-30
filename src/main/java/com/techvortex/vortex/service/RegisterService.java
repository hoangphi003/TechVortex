package com.techvortex.vortex.service;

import java.util.List;

import com.techvortex.vortex.entity.Account;

public interface RegisterService {

    void save(Account account);

    List<Account> CheckEmailUser(String email);

    List<Account> CheckUserName(String username);

    Account findByUserNameG(String userName);


 }
