package com.banking.account.query.infraestrutura.consumers;

import com.banking.account.commom.events.AbrirContaEvent;
import com.banking.account.commom.events.DepositarDinheiroEvent;
import com.banking.account.commom.events.FecharContaEvent;
import com.banking.account.commom.events.RetirarDinheiroEvent;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload AbrirContaEvent event, Acknowledgment ack);
    void consume(@Payload DepositarDinheiroEvent event, Acknowledgment ack);
    void consume(@Payload RetirarDinheiroEvent event, Acknowledgment ack);
    void consume(@Payload FecharContaEvent event, Acknowledgment ack);
}
