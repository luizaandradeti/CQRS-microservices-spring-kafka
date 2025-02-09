package com.banking.cqrs.core.commands;

import com.banking.cqrs.core.messages.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class BaseCommmand extends Message{

    public BaseCommmand(String id){
        super(id);


    }
}
