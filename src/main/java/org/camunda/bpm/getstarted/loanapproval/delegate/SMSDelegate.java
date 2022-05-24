package org.camunda.bpm.getstarted.loanapproval.delegate;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.getstarted.loanapproval.domain.Purchase;
import org.camunda.bpm.getstarted.loanapproval.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
       String phoneFrom = ""; //put the number from 
       String phoneTo = ""; ////put the number to

       final String ACCOUNT_SID = ""; //Your accountSID
       final String AUTH_TOKEN = ""; // Your AUTH_TOKEN

       Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

       if(Objects.nonNull(purchase.getStatus().equals(OrderStatus.CANCELED))){

           messageBody = "Your package was canceled com successfully.";
           messageTitle = "WebWorkIT";

           message = Message.creator(
                   new com.twilio.type.PhoneNumber(phoneFrom),
                   new com.twilio.type.PhoneNumber(phoneTo),
                   messageTitle)
                   .setBody(messageBody)
                   .create();
       }else if(Objects.isNull(purchase.getProduct().getName())){

            messageBody = "At the moment we don't have your product in stock :(";
            messageTitle = "WebWorkIT";

            message = Message.creator(
                    new com.twilio.type.PhoneNumber(phoneFrom),
                    new com.twilio.type.PhoneNumber(phoneTo),
                    messageTitle)
                    .setBody(messageBody)
                    .create();
        }else{
            messageBody = "Your product is beeing prepared to be delivered! :)";
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
