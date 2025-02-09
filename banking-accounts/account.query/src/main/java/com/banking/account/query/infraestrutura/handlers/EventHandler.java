package com.banking.account.query.infraestrutura.handlers;

import com.banking.account.commom.events.AbrirContaEvent;
import com.banking.account.commom.events.DepositarDinheiroEvent;
import com.banking.account.commom.events.FecharContaEvent;
import com.banking.account.commom.events.RetirarDinheiroEvent;



    public interface EventHandler {
        void on(AbrirContaEvent event);
        void on(DepositarDinheiroEvent event);
        void on(RetirarDinheiroEvent event);
        void on(FecharContaEvent event);


}
