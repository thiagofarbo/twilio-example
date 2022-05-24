package org.camunda.bpm.getstarted.loanapproval.consumer;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.getstarted.loanapproval.domain.Product;
import org.camunda.bpm.getstarted.loanapproval.domain.Purchase;
import org.camunda.bpm.getstarted.loanapproval.repository.ProductWarehouseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class MessageServiceReceiver {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private ProductWarehouseRepository productWarehouseRepository;

    @Transactional
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public <T> void receiverMessage(final Purchase purchase) {
//2.2
        ModelMapper mapper = new ModelMapper();
        final Purchase purchaseMapper = mapper.map(purchase, Purchase.class);

        final String productId = purchaseMapper.getProduct().getProductId();

        final Optional<Product> product = productWarehouseRepository.findProductByProductId(productId);

        if(product.isPresent()){
           purchaseMapper.setProduct(product.get());
        }
        corelateMessage(purchaseMapper);
    }

    public void corelateMessage(Purchase purchase) {
        processEngine.getRuntimeService().createMessageCorrelation("purchaseMessage").setVariable("purchase", purchase).correlateWithResult();
        System.out.println(purchase);
    }

    public Purchase getPurchaseEvent() {

        final Purchase purchase = (Purchase) processEngine.getRuntimeService().getVariable("purchaseProcess", "purchase");

        return purchase;
    }
}
