package com.devdeolho.controller;

import com.devdeolho.bus.CommandBus;
import com.devdeolho.domain.command.CreateProductCommand;
import com.devdeolho.domain.command.DeleteProductCommand;
import com.devdeolho.domain.command.UpdateProductCommand;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductCommandController {

    @Autowired
    private CommandBus commandBus;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid CreateProductCommand createProductCommand) {
        commandBus.execute(createProductCommand);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(
            @PathVariable("id") Integer id,
            @RequestBody @Valid UpdateProductCommand updateProductCommand
    ) {
        updateProductCommand.setId(id);
        commandBus.execute(updateProductCommand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Integer id) {
        commandBus.execute(new DeleteProductCommand(id));
    }

}
