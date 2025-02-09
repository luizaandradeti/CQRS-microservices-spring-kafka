package com.banking.account.cmd.api.command;

import com.banking.account.commom.dto.AccountType;

import com.banking.cqrs.core.commands.BaseCommmand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;


@Data
public class AbrirContaCommand extends BaseCommmand {

    private String accountHolder;
    private AccountType accountType;
    private double  openingBalance;

}
