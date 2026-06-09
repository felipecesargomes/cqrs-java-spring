package com.devdeolho.handler;

import com.devdeolho.domain.Product;
import com.devdeolho.domain.command.CreateProductCommand;
import com.devdeolho.domain.enums.Event;
import com.devdeolho.domain.message.Message;
import com.devdeolho.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateProductCommandHandler implements CommandHandler<CreateProductCommand> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, Message<?>> kafkaTemplate;

    @Override
    @Transactional
    public void handle(CreateProductCommand command) {
        final var product = Product.builder().id(null).imageUrl(command.getImageUrl()).name(command.getName()).description(command.getDescription()).value(command.getValue()).build();

        final var savedProduct = productRepository.save(product);
        kafkaTemplate.send("product-created", new Message<>(Event.PRODUCT_CREATED, savedProduct));
    }
}
