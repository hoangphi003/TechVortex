package com.techvortex.vortex.service;

import com.techvortex.vortex.entity.Account;

public interface LoginService {

    Account findById(String username);

    Account findbyemail(String email);
}
