package org.camunda.bpm.getstarted.loanapproval.repository;

import org.camunda.bpm.getstarted.loanapproval.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductWarehouseRepository extends MongoRepository<Product, String> {

    Optional<Product> findProductByProductId(String productId);

}
