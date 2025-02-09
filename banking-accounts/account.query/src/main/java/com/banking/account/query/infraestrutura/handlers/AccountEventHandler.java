package com.banking.account.query.infraestrutura.handlers;

import com.banking.account.commom.events.AbrirContaEvent;
import com.banking.account.commom.events.DepositarDinheiroEvent;
import com.banking.account.commom.events.FecharContaEvent;
import com.banking.account.commom.events.RetirarDinheiroEvent;

import com.banking.account.query.domain.AccountRepository;
import com.banking.account.query.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountEventHandler implements EventHandler{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void on(AbrirContaEvent event) {
        var bankAccount = BankAccount.builder()
                .id(event.getId())
                .accountHolder(event.getAccountHolder())
                .creationDate(event.getCreatedDate())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();

        accountRepository.save(bankAccount);
    }

    @Override
    public void on(DepositarDinheiroEvent event) {
        var bankAccount = accountRepository.findById(event.getId());
        if(bankAccount.isEmpty()){
            return;
        }

        var currentBalance = bankAccount.get().getBalance();
        var latestBalance = currentBalance + event.getAmount();
        bankAccount.get().setBalance(latestBalance);
        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(RetirarDinheiroEvent event) {
        var bankAccount = accountRepository.findById(event.getId());
        if(bankAccount.isEmpty()){
            return;
        }
        var currentBalance = bankAccount.get().getBalance();
        var latestBalance = currentBalance - event.getAmount();
        bankAccount.get().setBalance(latestBalance);
        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(FecharContaEvent event) {

        accountRepository.deleteById(event.getId());
    }
}
