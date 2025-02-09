package com.banking.cqrs.core.commands;

@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommmand> {
    void handle(T command);
}
