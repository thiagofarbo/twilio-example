package org.camunda.bpm.getstarted.loanapproval.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.getstarted.loanapproval.domain.Customer;
import org.camunda.bpm.getstarted.loanapproval.domain.Product;
import org.camunda.bpm.getstarted.loanapproval.domain.Purchase;
import org.camunda.bpm.getstarted.loanapproval.enums.OrderStatus;
import org.camunda.bpm.getstarted.loanapproval.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class PurchaseOrderDelegate implements JavaDelegate {

    @Autowired
    private PurchaseService service;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
    //1
        final Purchase purchase = service.createPurchase(builderPurchase());

        execution.setVariable("purchase", purchase);
    }

    private Purchase builderPurchase(){

        final Purchase purchase = new Purchase();

        final Product product = builderProduct();

        purchase.setOrderId(UUID.randomUUID());
        purchase.setProduct(product);
        purchase.setStatus(OrderStatus.ORDERED);
        purchase.setCustomer(builderCustomer());

        return purchase;
    }

    private Product builderProduct(){

        Product product = new Product();
        product.setProductId("60b7b41c2c9783299aaa8e69");
//        product.setProductId("1");
        return product;
    }

    private Customer builderCustomer(){

       final Customer customer = Customer.builder()
                .cpf("95207759000")
                .phone("112211222")
                .address("Rua Luis Scott 165, AP41 Torre Mare")
                .zipCode("06445555")
                .createdAt(LocalDateTime.now())
                .build();

        return customer;

    }
}
