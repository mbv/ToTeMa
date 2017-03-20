package edu.bsuir.totema.command.factory.service;

import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.impl.ViewAllCommand;
import edu.bsuir.totema.service.factory.ServiceFactory;
import edu.bsuir.totema.util.Pair;

import java.util.HashMap;

import static edu.bsuir.totema.command.resource.Constants.COMMAND_TYPE_ALL;
import static edu.bsuir.totema.command.resource.Constants.REQUEST_TYPE_GET;

public class EmployeeCommandFactory implements ServiceCommandFactory {

    private final static HashMap<Pair<String, String>, Command> commands = new HashMap<>();
    static {
        commands.put(Pair.of(COMMAND_TYPE_ALL, REQUEST_TYPE_GET), new ViewAllCommand(ServiceFactory.getInstance().getEmployeeService()));
    }

    @Override
    public Command defineCommand(String requestMethod, String commandType) {
        return commands.get(Pair.of(commandType, requestMethod));
    }
}
