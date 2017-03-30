package edu.bsuir.totema.command.impl;

import edu.bsuir.totema.command.ResourceCommand;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.entity.Entity;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.util.serialization.GsonProvider;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCommand implements ResourceCommand {

    private static final Logger logger = Logger.getLogger(ViewAllCommand.class);
    private Service _service;
    private long _resource_id;

    public ViewCommand(Service service, long resource_id) {
        _service = service;
        _resource_id = resource_id;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result;
        try {
            Entity entity = _service.getById(_resource_id);
            if (entity != null) {
                result = GsonProvider.getGson().toJson(entity);
            } else {
                logger.info("Getting non-existent entity with id:" + _resource_id);
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                result = "{\"error\":\"Entity not found\"}";
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
