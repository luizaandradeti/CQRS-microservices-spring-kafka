package com.banking.account.cmd.domain;

import com.banking.account.cmd.api.command.AbrirContaCommand;
import com.banking.account.commom.events.AbrirContaEvent;
import com.banking.account.commom.events.FecharContaEvent;

import com.banking.account.commom.events.DepositarDinheiroEvent;
import com.banking.account.commom.events.RetirarDinheiroEvent;
import com.banking.cqrs.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;


import java.util.Date;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {
    private Boolean active;
    private double balance;

    public double getBalance(){
        return this.balance;
    }

    public AccountAggregate(AbrirContaCommand command){
        raiseEvent(AbrirContaEvent.builder()
                .id(command.getId())
                .accountHolder(command.getAccountHolder())
                .createdDate(new Date())
                .accountType(command.getAccountType())
                .openingBalance(command.getOpeningBalance())
                .build());
    }

    public void apply(AbrirContaEvent event){
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(double amount){
        if(!this.active){
            throw new IllegalStateException("Los fondos no pueden ser depositados en esta cuenta");
        }

        if(amount <= 0){
            throw new IllegalStateException("El deposito de dinero no puede ser cero menos que cero");
        }

        raiseEvent(DepositarDinheiroEvent.builder()
                .id(this.id)
                .amount(amount)
                .build());

    }

    public void apply(DepositarDinheiroEvent event){
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void withdrawFunds(double amount){
        if(!this.active){
            throw new IllegalStateException("La cuenta bancaria esta cerrada");
        }
        raiseEvent(RetirarDinheiroEvent.builder()
                .id(this.id)
                .amount(amount)
                .build());
    }

    public void apply(RetirarDinheiroEvent event){
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void closeAccount(){
        if(!active){
            throw new IllegalStateException("La cuenta de banco esta cerrada");
        }

        raiseEvent(FecharContaEvent.builder()
                .id(this.id)
                .build());
    }

    public void apply(FecharContaEvent event){
        this.id = event.getId();
        this.active = false;

    }


}
