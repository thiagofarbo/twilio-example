package org.camunda.bpm.getstarted.loanapproval.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.getstarted.loanapproval.domain.CustomerError;
import org.camunda.bpm.getstarted.loanapproval.domain.Purchase;
import org.camunda.bpm.getstarted.loanapproval.repository.CustomerErrorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerErrorLogDelegate implements JavaDelegate {

    @Autowired
    private CustomerErrorRepository customerErrorRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        final Purchase purchase = (Purchase) execution.getVariable("purchase");

        ModelMapper modelMapper = new ModelMapper();
        final CustomerError customer = modelMapper.map(purchase.getCustomer(), CustomerError.class);

        customerErrorRepository.save(customer);

    }
}
