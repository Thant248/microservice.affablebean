package com.example.paymentgateway.service;

import com.example.paymentgateway.dao.AccountDao;
import com.example.paymentgateway.entity.Account;
import com.example.paymentgateway.exception.InsufficientAmount;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountDao accountDao;


    public double getBalance(String email) {
        return getByEmail(email).orElseThrow(()->new EntityNotFoundException(email +"not found")).getAmount();
     // return account.getAmount();
    }

    @Transactional
    public double deposit(String email,double amount){
        double existingAmount = getBalance(email);
        double updatedAmount = (existingAmount+amount);
         Optional<Account> accountOptional;
        accountOptional = getByEmail(email);
        if (accountOptional.isPresent()){
            Account account1 = accountOptional.get();
            account1.setAmount(updatedAmount);
            accountDao.save(account1);
            return updatedAmount;
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
    @Transactional
    public double withDraw(String email,double amount){
        double balance = getBalance(email);
        if (balance <  amount) {
            throw  new InsufficientAmount();
        }
        double updateAmount = balance - amount;
        Optional<Account> acountOptional = getByEmail(email);
        if (acountOptional.isPresent()){
            Account account1 = acountOptional.get();
            account1.setAmount(updateAmount);
            accountDao.save(account1);
            return updateAmount;
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    private Optional<Account> getByEmail(String email) {
        return accountDao.findAccountByEmail(email);
    }

    public void transfer(String fromEmail,String toEmail,double amount){
        withDraw(fromEmail,amount);
        deposit(toEmail,amount);

    }


}

