package org.camunda.bpm.getstarted.loanapproval.delegate;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.getstarted.loanapproval.domain.Customer;
import org.camunda.bpm.getstarted.loanapproval.domain.Purchase;
import org.camunda.bpm.getstarted.loanapproval.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDelegate implements JavaDelegate {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        final Purchase purchase = (Purchase) execution.getVariable("purchase");

        try {

            final Customer customer = purchase.getCustomer();

//            customer.setPurchase(purchase);

            customer.setProduct(purchase.getProduct());

//            purchase.setCustomer(customer);

            final Customer customerSaved = customerRepository.save(customer);

            purchase.setCustomer(customerSaved);
            purchase.setProduct(customer.getProduct());

            execution.setVariable("purchase", purchase);

        }catch (Exception e){
            throw new BpmnError("customer_error");
        }
    }
}
