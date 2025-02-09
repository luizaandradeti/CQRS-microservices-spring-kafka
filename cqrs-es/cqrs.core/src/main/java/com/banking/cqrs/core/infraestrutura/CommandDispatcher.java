package com.banking.cqrs.core.infraestrutura;


import com.banking.cqrs.core.commands.BaseCommmand;
import com.banking.cqrs.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommmand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommmand command);
}
