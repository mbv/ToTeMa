package edu.bsuir.totema.command.impl;

import com.google.gson.Gson;
import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ViewAllCommand implements Command {

    private Service _service;

    public ViewAllCommand(Service service) {
        _service = service;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result = "";
        try {
            Gson gson = new Gson();

            result = gson.toJson(_service.getAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return result;
    }
}
