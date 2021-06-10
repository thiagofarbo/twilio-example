package org.camunda.bpm.getstarted.loanapproval.repository;

import org.camunda.bpm.getstarted.loanapproval.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findCustomerByCpf(String cpf);
}
