package org.camunda.bpm.getstarted.loanapproval.repository;

import org.camunda.bpm.getstarted.loanapproval.domain.CustomerError;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerErrorRepository extends MongoRepository<CustomerError, String> {
}
