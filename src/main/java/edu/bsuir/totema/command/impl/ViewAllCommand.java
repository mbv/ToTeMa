package edu.bsuir.totema.command.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAllCommand implements Command {

    private Service _service;

    public ViewAllCommand(Service service) {
        _service = service;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result;
        try {
            final GsonBuilder builder = new GsonBuilder();
            builder.excludeFieldsWithoutExposeAnnotation();
            final Gson gson = builder.create();

            result = gson.toJson(_service.getAll());
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
