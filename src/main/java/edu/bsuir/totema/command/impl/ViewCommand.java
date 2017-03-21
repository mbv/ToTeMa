package edu.bsuir.totema.command.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.bsuir.totema.command.ResourceCommand;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCommand implements ResourceCommand {

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
            final GsonBuilder builder = new GsonBuilder();
            builder.excludeFieldsWithoutExposeAnnotation();
            final Gson gson = builder.create();

            result = gson.toJson(_service.getById(_resource_id));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
