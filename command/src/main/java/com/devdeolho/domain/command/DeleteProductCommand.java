package com.devdeolho.domain.command;

public class DeleteProductCommand implements Command {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public DeleteProductCommand(Integer id) {
        this.id = id;
    }

    public DeleteProductCommand() {
    }
}
