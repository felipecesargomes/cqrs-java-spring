package com.devdeolho.bus;

import ch.qos.logback.core.util.StringUtil;
import com.devdeolho.domain.command.Command;
import com.devdeolho.handler.CommandHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandBus {

    @Autowired
    private ApplicationContext context;

    public void execute(final Command command) {
        try {
            String handlerBeanName = StringUtil.lowercaseFirstLetter(command.getClass().getSimpleName() + "Handler");
            CommandHandler handler = (CommandHandler) context.getBean(handlerBeanName);
            handler.handle(command);
        }catch (Exception e) {
            throw new RuntimeException("Error executing command: " + command.getClass().getSimpleName(), e);
        }
    }

}
