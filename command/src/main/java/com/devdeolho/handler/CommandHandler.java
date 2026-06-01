package com.devdeolho.handler;

import com.devdeolho.domain.command.Command;

public interface CommandHandler<C extends Command> {
    void handle(C command);
}
