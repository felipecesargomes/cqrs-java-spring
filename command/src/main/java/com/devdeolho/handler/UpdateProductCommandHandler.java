package com.devdeolho.handler;

import com.devdeolho.domain.command.UpdateProductCommand;
import com.devdeolho.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductCommandHandler implements CommandHandler<UpdateProductCommand> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void handle(UpdateProductCommand command) {
        var productToBeSaved = productRepository.findById(command.getId())
                .orElseThrow(IllegalArgumentException::new)
                .toBuilder()
                .imageUrl(command.getImageUrl())
                .name(command.getName())
                .description(command.getDescription())
                .value(command.getValue())
                .build();

        productRepository.save(productToBeSaved);
    }
}
