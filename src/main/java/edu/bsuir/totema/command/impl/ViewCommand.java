package edu.bsuir.totema.command.impl;

import com.google.gson.Gson;
import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCommand implements Command {

    private Service _service;

    public ViewCommand(Service service) {
        _service = service;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = "";
        try {
            Gson gson = new Gson();

            result = gson.toJson(_service.getById(1));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return result;
    }
}
