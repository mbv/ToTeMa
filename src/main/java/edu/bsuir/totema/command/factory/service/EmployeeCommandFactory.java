package edu.bsuir.totema.command.factory.service;

import edu.bsuir.totema.command.impl.*;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.factory.ServiceFactory;
import edu.bsuir.totema.util.Pair;

import static edu.bsuir.totema.command.resource.Constants.*;

public class EmployeeCommandFactory extends ServiceCommandFactory {

    static {
        commands.put(Pair.of(COMMAND_TYPE_ALL, REQUEST_TYPE_GET), new ViewAllCommand(ServiceFactory.getInstance().getEmployeeService()));
        commands.put(Pair.of(COMMAND_TYPE_ALL, REQUEST_TYPE_POST), new CreateCommand(ServiceFactory.getInstance().getEmployeeService()));

        commandsResource.put(Pair.of(COMMAND_TYPE_RESOURCE, REQUEST_TYPE_GET), ViewCommand.class);
        commandsResource.put(Pair.of(COMMAND_TYPE_RESOURCE, REQUEST_TYPE_PUT), UpdateCommand.class);
        commandsResource.put(Pair.of(COMMAND_TYPE_RESOURCE, REQUEST_TYPE_DELETE), DeleteCommand.class);
    }

    @Override
    Service getService() {
        return ServiceFactory.getInstance().getEmployeeService();
    }
}
