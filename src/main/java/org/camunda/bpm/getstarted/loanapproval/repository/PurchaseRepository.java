package org.camunda.bpm.getstarted.loanapproval.repository;

import org.camunda.bpm.getstarted.loanapproval.domain.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PurchaseRepository extends MongoRepository<Purchase, UUID> {
}
