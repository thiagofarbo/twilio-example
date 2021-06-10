package org.camunda.bpm.getstarted.loanapproval.service;

import org.camunda.bpm.getstarted.loanapproval.domain.Product;
import org.camunda.bpm.getstarted.loanapproval.repository.ProductWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductWarehouseRepository repository;

    public Optional<Product> findProductById(final String id){

        final Optional<Product> product = repository.findProductByProductId(id);

        return product;
    }
}
