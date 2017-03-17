package edu.bsuir.totema.command.factory.service;

import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.impl.ViewAllCommand;
import edu.bsuir.totema.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

import static edu.bsuir.totema.command.resource.Constants.COMMAND_VIEW_ALL;

public class EmployeeCommandFactory implements ServiceCommandFactory {

    private final static HashMap<String, Command> commands = new HashMap<>();
    static {
        commands.put(COMMAND_VIEW_ALL, new ViewAllCommand(ServiceFactory.getInstance().getEmployeeService()));
    }

    @Override
    public Command defineCommand(HttpServletRequest request) {
        return commands.get(COMMAND_VIEW_ALL);
    }
}
