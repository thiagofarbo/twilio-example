package org.camunda.bpm.getstarted.loanapproval.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.getstarted.loanapproval.domain.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Component
public class SMSDelegate implements JavaDelegate {

    private String messageId;

    @Autowired
    private ProcessEngine processEngine;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        final Purchase purchase = (Purchase) execution.getVariable("purchase");

        purchase.getCustomer().setMessageId(UUID.randomUUID().toString());

        this.builderSMSMessage(purchase);

        execution.setVariable("purchase", purchase);
    }

    private void builderSMSMessage(final Purchase purchase){

       Message message;
       String messageBody = null;
       String messageTitle = null;
       String phoneFrom = "+5511933309874";
       String phoneTo = "+14178151939";

       final String ACCOUNT_SID = "ACf80d7d1e0a85b80d497a213a03a6a5fd";
       final String AUTH_TOKEN = "4126c1700757923e0144d2df6381ed36";

       Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        if(Objects.isNull(purchase.getProduct().getName())){
            messageBody = "No momento nao temos seu produto em estoque :(";
            messageTitle = "WebWorkIT";

            message = Message.creator(
                    new com.twilio.type.PhoneNumber(phoneFrom),
                    new com.twilio.type.PhoneNumber(phoneTo),
                    messageTitle)
                    .setBody(messageBody)
                    .create();
        }else{
            messageBody = "Seu produto esta sendo preparado para envio! :)";
            messageTitle = "WebWorkIT";
            message = Message.creator(
                    new com.twilio.type.PhoneNumber(phoneFrom),
                    new com.twilio.type.PhoneNumber(phoneTo),
                    messageTitle)
                    .setBody(messageBody)
                    .create();
        }
        log.warn(String.format("Message Id %s", message.getSid()));
    }
}
