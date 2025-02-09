package com.banking.account.cmd;

import com.banking.cqrs.core.infraestrutura.CommandDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.banking.account.cmd.api.command.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommandApplication {

	@Autowired
	private CommandDispatcher commandDispatcher;

	@Autowired
	private CommandHandler commandHandler;

	public static void main(String[] args) {

		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers(){
		commandDispatcher.registerHandler(AbrirContaCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(DepositarDinheiroCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(RetirarDinheiroCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(FecharContaCommand.class, commandHandler::handle);
	}



}
