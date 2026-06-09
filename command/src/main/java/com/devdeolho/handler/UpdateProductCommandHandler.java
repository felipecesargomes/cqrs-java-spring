package com.devdeolho.handler;

import com.devdeolho.domain.command.UpdateProductCommand;
import com.devdeolho.domain.message.Message;
import com.devdeolho.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateProductCommandHandler implements CommandHandler<UpdateProductCommand> {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private KafkaTemplate<String, Message<?>> kafkaTemplate;


    @Override
    @Transactional
    public void handle(UpdateProductCommand command) {
        var productToBeSaved = productRepository.findById(command.getId())
                .orElseThrow(IllegalArgumentException::new)
                .toBuilder()
                .imageUrl(command.getImageUrl())
                .name(command.getName())
                .description(command.getDescription())
                .value(command.getValue())
                .build();

        final var updatedProduct = productRepository.save(productToBeSaved);
        kafkaTemplate.send("product-updated", new Message<>(com.devdeolho.domain.enums.Event.PRODUCT_UPDATED, updatedProduct));
    }
}
