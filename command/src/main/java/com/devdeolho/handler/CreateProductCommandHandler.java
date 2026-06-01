package com.devdeolho.handler;

import com.devdeolho.domain.Product;
import com.devdeolho.domain.command.CreateProductCommand;
import com.devdeolho.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateProductCommandHandler implements CommandHandler<CreateProductCommand> {

    @Autowired
    private ProductRepository repository;

    @Override
    public void handle(CreateProductCommand command) {
        final var product = Product.builder().id(null).imageUrl(command.getImageUrl()).name(command.getName()).description(command.getDescription()).value(command.getValue()).build();

        repository.save(product);
    }
}
