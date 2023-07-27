package com.example.paymentgateway.dao;

import com.example.paymentgateway.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountDao extends JpaRepository<Account,Integer> {

    Optional<Account> findAccountByEmail(String email);
}
