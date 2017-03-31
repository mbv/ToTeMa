package edu.bsuir.totema.command.factory.service;

import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.ResourceCommand;
import edu.bsuir.totema.command.factory.UnresolvedCommand;
import edu.bsuir.totema.command.impl.*;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.util.Pair;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static edu.bsuir.totema.command.resource.Constants.*;
import static edu.bsuir.totema.command.resource.Constants.COMMAND_TYPE_RESOURCE;
import static edu.bsuir.totema.command.resource.Constants.REQUEST_TYPE_DELETE;

public abstract class ServiceCommandFactory {
    private static final Logger logger = Logger.getLogger(ServiceCommandFactory.class);

    HashMap<Pair<String, String>, Command> commands = new HashMap<>();
    HashMap<Pair<String, String>, Class<? extends ResourceCommand>> commandsResource = new HashMap<>();
    {
        initializeCommands();
    }

    void initializeCommands() {
        commands.put(Pair.of(COMMAND_TYPE_ALL, REQUEST_TYPE_GET), new ViewAllCommand(getService()));
        commands.put(Pair.of(COMMAND_TYPE_ALL, REQUEST_TYPE_POST), new CreateCommand(getService()));

        commandsResource.put(Pair.of(COMMAND_TYPE_RESOURCE, REQUEST_TYPE_GET), ViewCommand.class);
        commandsResource.put(Pair.of(COMMAND_TYPE_RESOURCE, REQUEST_TYPE_PUT), UpdateCommand.class);
        commandsResource.put(Pair.of(COMMAND_TYPE_RESOURCE, REQUEST_TYPE_DELETE), DeleteCommand.class);
    }

    public Command defineCommand(String requestMethod, String commandType, Integer resourceId) {
        Pair<String, String> commandId = Pair.of(commandType, requestMethod);
        Command resultCommand = new UnresolvedCommand();
        if (commands.containsKey(commandId)) {
            resultCommand = commands.get(commandId);
        } else if (commandsResource.containsKey(commandId)) {
            try {
                Class<? extends Command> commandClass = commandsResource.get(commandId);
                Class<?>[] constructorArguments = new Class<?>[]{Service.class, long.class};
                Constructor<? extends Command> declaredConstructor = commandClass.getDeclaredConstructor(constructorArguments);
                resultCommand = declaredConstructor.newInstance(getService(), resourceId);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                logger.warn("Error with creating command \"" + commandId + "\"");
            }
        } else {
            logger.warn("Command with specified id not found. (id: \"" + commandId + "\")");
        }
        return resultCommand;
    }

    abstract Service getService();
}
