package edu.bsuir.totema.command.factory.service;

import edu.bsuir.totema.command.impl.CreateCommand;
import edu.bsuir.totema.command.impl.ViewAllCommand;
import edu.bsuir.totema.command.impl.ViewCommand;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.factory.ServiceFactory;
import edu.bsuir.totema.util.Pair;

import static edu.bsuir.totema.command.resource.Constants.*;

public class EmployeeCommandFactory extends ServiceCommandFactory {

    static {
        commands.put(Pair.of(COMMAND_TYPE_ALL, REQUEST_TYPE_GET), new ViewAllCommand(ServiceFactory.getInstance().getEmployeeService()));
        commands.put(Pair.of(COMMAND_TYPE_ALL, REQUEST_TYPE_POST), new CreateCommand(ServiceFactory.getInstance().getEmployeeService()));

        commandsResource.put(Pair.of(COMMAND_TYPE_RESOURCE, REQUEST_TYPE_GET), ViewCommand.class);
    }

    @Override
    Service getService() {
        return ServiceFactory.getInstance().getEmployeeService();
    }
}
