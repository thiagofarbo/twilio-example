package org.camunda.bpm.getstarted.loanapproval.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    ORDERED("Ordered"),
    CANCELED("Canceled"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered");

    private String description;

    OrderStatus(String description) {
        this.description = description;
    }
}
