package com.devdeolho.handler;

import com.devdeolho.domain.command.DeleteProductCommand;
import com.devdeolho.domain.enums.Event;
import com.devdeolho.domain.message.Message;
import com.devdeolho.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteProductCommandHandler implements CommandHandler<DeleteProductCommand> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, Message<?>> kafkaTemplate;


    @Override
    @Transactional
    public void handle(DeleteProductCommand command) {
        productRepository.findById(command.getId()).orElseThrow(IllegalArgumentException::new);
        productRepository.deleteById(command.getId());
        kafkaTemplate.send("product-deleted", new Message<>(Event.PRODUCT_DELETED, command.getId()));
    }
}
