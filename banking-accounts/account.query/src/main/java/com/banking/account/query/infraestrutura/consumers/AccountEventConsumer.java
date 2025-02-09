package com.banking.account.query.infraestrutura.consumers;

import com.banking.account.commom.events.AbrirContaEvent;
import com.banking.account.commom.events.DepositarDinheiroEvent;
import com.banking.account.commom.events.FecharContaEvent;
import com.banking.account.commom.events.RetirarDinheiroEvent;

import com.banking.account.query.infraestrutura.handlers.EventHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class AccountEventConsumer implements EventConsumer {

    @Autowired
    private EventHandler eventHandler;

    @KafkaListener(topics="AbrirContaEvent", groupId="${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload AbrirContaEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics="DepositarDinheiroEvent", groupId="${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload DepositarDinheiroEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics="RetirarDinheiroEvent", groupId="${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload RetirarDinheiroEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @KafkaListener(topics="FecharContaEvent", groupId="${spring.kafka.consumer.group-id}")
    @Override
    public void consume(@Payload FecharContaEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
}
