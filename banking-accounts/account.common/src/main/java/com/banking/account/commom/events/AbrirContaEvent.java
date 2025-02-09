package com.banking.account.commom.events;

import com.banking.account.commom.dto.AccountType;
import com.banking.cqrs.core.commands.BaseCommmand;
import com.banking.cqrs.core.events.BaseEvent;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AbrirContaEvent extends BaseEvent{
    private String accountHolder;
    private AccountType accountType;
    private Date createdDate;
    private double openingBalance;

}
