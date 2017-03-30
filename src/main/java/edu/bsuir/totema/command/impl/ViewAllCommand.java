package edu.bsuir.totema.command.impl;

import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.util.serialization.GsonProvider;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAllCommand implements Command {

    private static final Logger logger = Logger.getLogger(ViewAllCommand.class);
    private Service _service;

    public ViewAllCommand(Service service) {
        _service = service;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result;
        try {
            result = GsonProvider.getGson().toJson(_service.getAll());
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
