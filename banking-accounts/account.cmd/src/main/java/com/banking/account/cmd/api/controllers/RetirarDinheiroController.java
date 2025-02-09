package com.banking.account.cmd.api.controllers;


import com.banking.account.cmd.api.command.RetirarDinheiroCommand;

import com.banking.account.commom.dto.BaseResponse;
import com.banking.account.commom.events.RetirarDinheiroEvent;

import com.banking.cqrs.core.exceptions.AggregateNotFoundException;
import com.banking.cqrs.core.infraestrutura.CommandDispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path ="/api/v1/withDrawFunds")
public class RetirarDinheiroController {

    private final Logger logger = Logger.getLogger(RetirarDinheiroController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;


    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> withDrawFunds(@PathVariable(value="id") String id,
                                                      @RequestBody RetirarDinheiroCommand command){
        try{
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(new BaseResponse("El retiro de dinero fue exitoso"), HttpStatus.OK);
        }catch(IllegalStateException  | AggregateNotFoundException e){
            logger.log(Level.WARNING, MessageFormat.format("El cliente envio un request con errores {0} ", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            var safeErrorMessage = MessageFormat.format("Errores mientras procesaba el request {id}", id);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
