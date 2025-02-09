package com.banking.account.cmd.api.command;

import com.banking.account.commom.dto.AccountType;
import com.banking.cqrs.core.commands.BaseCommmand;

public class FecharContaCommand extends BaseCommmand {
    public FecharContaCommand(String id) {
        super(id);
    }
}