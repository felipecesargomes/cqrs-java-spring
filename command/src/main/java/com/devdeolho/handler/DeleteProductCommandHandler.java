package com.devdeolho.handler;

import com.devdeolho.domain.command.DeleteProductCommand;
import com.devdeolho.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductCommandHandler implements CommandHandler<DeleteProductCommand> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void handle(DeleteProductCommand command) {
        productRepository.findById(command.getId()).orElseThrow(IllegalArgumentException::new);
        productRepository.deleteById(command.getId());
    }
}
