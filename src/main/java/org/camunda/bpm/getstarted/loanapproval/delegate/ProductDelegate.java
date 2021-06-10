package org.camunda.bpm.getstarted.loanapproval.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.getstarted.loanapproval.domain.Product;
import org.camunda.bpm.getstarted.loanapproval.domain.Purchase;
import org.camunda.bpm.getstarted.loanapproval.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ProductDelegate implements JavaDelegate {

    @Autowired
    private ProductService service;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
//3
        final Purchase purchase = (Purchase) execution.getVariable("purchase");

        final String productId = purchase.getProduct().getProductId();

        final Optional<Product> product = service.findProductById(productId);

        if(product.isPresent()){

            purchase.setProduct(product.get());

            execution.setVariable("purchase", purchase);
        }else{
            purchase.setProduct(new Product());
            execution.setVariable("purchase", purchase);
        }
    }
}
