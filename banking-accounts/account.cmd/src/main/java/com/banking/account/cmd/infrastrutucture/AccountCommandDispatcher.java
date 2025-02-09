package com.banking.account.cmd.infrastrutucture;

import com.banking.cqrs.core.commands.BaseCommmand;
import com.banking.cqrs.core.commands.BaseCommmand;
import com.banking.cqrs.core.commands.CommandHandlerMethod;
import com.banking.cqrs.core.infraestrutura.CommandDispatcher;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AccountCommandDispatcher implements CommandDispatcher {
    private final Map<Class<? extends BaseCommmand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommmand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(BaseCommmand command) {
        var handlers = routes.get(command.getClass());
        if(handlers == null || handlers.size() == 0){
            throw new RuntimeException("El command handler no fue registrado");
        }

        if(handlers.size() > 1){
            throw new RuntimeException("No puede enviar un command que tiene mas de un handler");
        }

        handlers.get(0).handle(command);
    }


    }


