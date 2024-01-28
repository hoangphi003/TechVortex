package com.techvortex.vortex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techvortex.vortex.entity.Account;
import java.util.List;

public interface LoginDao extends JpaRepository<Account, String> {

    @Query("select a from Account a where a.UserName = :name")
    Account isFindUserNameById(@Param("name") String username);

}