package edu.bsuir.totema.command.factory.service;

import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.ResourceCommand;
import edu.bsuir.totema.command.factory.UnresolvedCommand;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.util.Pair;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public abstract class ServiceCommandFactory {
    private static final Logger logger = Logger.getLogger(ServiceCommandFactory.class);

    HashMap<Pair<String, String>, Command> commands = new HashMap<>();
    HashMap<Pair<String, String>, Class<? extends ResourceCommand>> commandsResource = new HashMap<>();

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
