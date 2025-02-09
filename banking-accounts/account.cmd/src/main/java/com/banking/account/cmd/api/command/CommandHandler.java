package com.banking.account.cmd.api.command;

import com.banking.account.commom.events.FecharContaEvent;

public interface CommandHandler {
    void handle(AbrirContaCommand command);
    void handle(DepositarDinheiroCommand command);
    void handle(RetirarDinheiroCommand command);
    void handle(FecharContaCommand command);
}
