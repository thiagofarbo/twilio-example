package org.camunda.bpm.getstarted.loanapproval.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.getstarted.loanapproval.domain.Purchase;
import org.camunda.bpm.getstarted.loanapproval.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.UUID;

@Slf4j
@Component
public class SMSDelegate implements JavaDelegate {

    private String messageId;

    @Autowired
    private ProcessEngine processEngine;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

//       final String ACCOUNT_SID = "ACf80d7d1e0a85b80d497a213a03a6a5fd";
//       final String AUTH_TOKEN = "4126c1700757923e0144d2df6381ed36";
//
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//       final Message message = Message.creator(
//                new com.twilio.type.PhoneNumber("+5511933309874"),
//                new com.twilio.type.PhoneNumber("+14178151939"),
//                "OII, estou fazedo alguns testes :)")
//                .setBody("Oi, sou eu....")
//                .create();
//
//        System.out.println(message.getSid());
//
        final Purchase purchase = (Purchase) execution.getVariable("purchase");

        purchase.getCustomer().setMessageId(UUID.randomUUID().toString());

        execution.setVariable("purchase", purchase);
    }
}
