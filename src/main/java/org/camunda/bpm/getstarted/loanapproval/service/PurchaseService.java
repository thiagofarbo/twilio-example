package org.camunda.bpm.getstarted.loanapproval.service;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.getstarted.loanapproval.domain.Product;
import org.camunda.bpm.getstarted.loanapproval.domain.Purchase;
import org.camunda.bpm.getstarted.loanapproval.enums.OrderStatus;
import org.camunda.bpm.getstarted.loanapproval.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository repository;

    @Autowired
    private ProductService productService;

    private Purchase purchase;

    public Purchase createPurchase(final Purchase purchaseRequest){
//1.1
        final Optional<Product> product = this.productService.findProductById(purchaseRequest.getProduct().getProductId());

        if(product.isPresent()){

            purchaseRequest.setProduct(product.get());

           return purchase = repository.save(purchaseRequest);
        }
        return purchaseRequest;
    }

    public Purchase cancelPurchaseOrder(final Purchase purchaseRequest){

        final Optional<Purchase> purchase = repository.findById(purchaseRequest.getOrderId());

        try {

            if (purchase.isPresent()) {
                purchase.get().setStatus(OrderStatus.CANCELED);
                repository.save(purchase.get());
            }

        } catch (Exception e) {
            throw new BpmnError("customer_error");
        }

        return purchase.get();
    }
}
