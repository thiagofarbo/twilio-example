package org.camunda.bpm.getstarted.loanapproval.delegate;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.getstarted.loanapproval.domain.Purchase;
import org.camunda.bpm.getstarted.loanapproval.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CancelPurchaseOrderDelegate implements JavaDelegate {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private PurchaseService service;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        final Purchase purchase = (Purchase) execution.getVariable("purchase");

        execution.setVariable("purchase", purchase);

    }
}
