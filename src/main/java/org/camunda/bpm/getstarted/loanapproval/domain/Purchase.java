package org.camunda.bpm.getstarted.loanapproval.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.camunda.bpm.getstarted.loanapproval.enums.OrderStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID orderId = UUID.randomUUID() ;

    private Customer customer;

    private Product product;

    private OrderStatus status;
}
