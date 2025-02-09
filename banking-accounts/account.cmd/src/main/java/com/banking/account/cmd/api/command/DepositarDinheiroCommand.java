package com.banking.account.cmd.api.command;

import com.banking.cqrs.core.commands.BaseCommmand;

import lombok.Data;

@Data
public class DepositarDinheiroCommand extends BaseCommmand {

    private double amount;
}