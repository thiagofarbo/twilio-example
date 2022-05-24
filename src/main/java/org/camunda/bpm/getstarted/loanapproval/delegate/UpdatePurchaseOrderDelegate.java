package org.camunda.bpm.getstarted.loanapproval.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.getstarted.loanapproval.domain.Purchase;
import org.camunda.bpm.getstarted.loanapproval.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdatePurchaseOrderDelegate implements JavaDelegate {

    @Autowired
    private PurchaseService service;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        final Purchase purchase = (Purchase) execution.getVariable("purchase");

        final Purchase purchaseCanceled = service.cancelPurchaseOrder(purchase);

        execution.setVariable("purchase", purchaseCanceled);

        log.warn(String.format("Order has been canceled %s", purchaseCanceled));
    }
}
