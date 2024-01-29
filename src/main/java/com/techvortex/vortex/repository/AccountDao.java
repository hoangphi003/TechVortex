package com.techvortex.vortex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techvortex.vortex.entity.Account;
@Repository
public interface AccountDao extends JpaRepository<Account,String> {
    
}
