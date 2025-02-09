package com.banking.account.cmd.api.command;



import com.banking.account.cmd.api.command.AbrirContaCommand;
import com.banking.account.cmd.api.command.DepositarDinheiroCommand;
import com.banking.account.cmd.api.command.FecharContaCommand;
import com.banking.account.cmd.api.command.RetirarDinheiroCommand;
import com.banking.account.cmd.domain.AccountAggregate;
import com.banking.account.commom.events.FecharContaEvent;
import com.banking.cqrs.core.handlers.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCommandHandler implements CommandHandler {
    @Autowired
    private EventSourcingHandler<AccountAggregate> eventSourcingHandler;

    @Override
    public void handle(AbrirContaCommand command) {
        var aggregate = new AccountAggregate(command);
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(DepositarDinheiroCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.depositFunds(command.getAmount());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(RetirarDinheiroCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        if (command.getAmount() > aggregate.getBalance()){
            throw new IllegalStateException("Insuficientes fondos, no se puede retirar dinero");
        }

        aggregate.withdrawFunds(command.getAmount());
        eventSourcingHandler.save(aggregate);
    }

    @Override
    public void handle(FecharContaCommand command) {
        var aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.closeAccount();
        eventSourcingHandler.save(aggregate);
    }
}
