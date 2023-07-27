package com.example.paymentgateway.controller;


import com.example.paymentgateway.entity.Account;
import com.example.paymentgateway.service.AccountService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.PrintStream;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

   record RequestAccount(String email,double amount){}
    record ResponseAccount(String email,double amount){}
   // record ResponseTransferAccount(PrintStream msg){}
    record RequestTransferAccount(@JsonProperty("from_email") String fromEmail,@JsonProperty("to_email") String toEmail, double amount){}
    @GetMapping("/getbalance")
    public double getBalance(@PathVariable("email")String email){
        return accountService.getBalance(email);
    }

    @PostMapping("/withDraw")
    public ResponseAccount withDraw(@RequestBody RequestAccount requestAccount){
      double amount =  accountService.withDraw(requestAccount.email,requestAccount.amount);
      return new ResponseAccount(
              requestAccount.email,
              amount
      );

    }

    @PostMapping("/deposit")
    public ResponseAccount deposit(@RequestBody ResponseAccount responseAccount){
       double amount = accountService.deposit(responseAccount.email, responseAccount.amount);
       return new ResponseAccount(responseAccount.email,amount);
    }

//    @PostMapping("/transfer")
//    public ResponseTransferAccount transfer(@RequestBody RequestTransferAccount requestTransferAccount){
//     accountService.transfer(requestTransferAccount.fromEmail, requestTransferAccount.toEmail, requestTransferAccount.amount);
//     return new ResponseTransferAccount(
//             System.out.printf("%s transfer $%s to %s",requestTransferAccount.fromEmail,requestTransferAccount.toEmail,requestTransferAccount.toEmail)
//     );
//    }
record ResponseTransferAccount(String msg) {}
    @PostMapping("/transfer")
    public ResponseTransferAccount transfer(@RequestBody RequestTransferAccount account) {
        accountService.transfer(account.fromEmail,account.toEmail,account.amount);
        return new ResponseTransferAccount(String.format(
                "%s transfer $%s to %s successfully",
                account.fromEmail,account.amount,account.toEmail
        ));
    }
}
