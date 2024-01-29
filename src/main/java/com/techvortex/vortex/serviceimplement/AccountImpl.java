package com.techvortex.vortex.serviceimplement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techvortex.vortex.entity.Account;
import com.techvortex.vortex.repository.AccountDao;
import com.techvortex.vortex.service.AccountService;

import javax.transaction.Transactional;
@Service
public class AccountImpl implements AccountService {
         
    @Autowired
	AccountDao accountDAO;

    @Override
    public List<Account> findAll() {
        // TODO Auto-generated method stub
        return accountDAO.findAll();
    }

    @Override
    public Account create(Account account) {
        // TODO Auto-generated method stub
        return accountDAO.save(account);
    }

    @Override
    public Account update(Account account) {
        // TODO Auto-generated method stub
        return accountDAO.save(account);
    }

    @Override
    public void delete(Account account) {
        // TODO Auto-generated method stub
        accountDAO.delete(account);
    }

    @Override
	@Transactional
	public Account findById(String username) {
		return accountDAO.findById(username).get();
	}


   
}
