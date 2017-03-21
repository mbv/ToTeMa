package edu.bsuir.totema.command.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.entity.Employee;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCommand implements Command {

    private static final Logger logger = Logger.getLogger(ViewAllCommand.class);
    private Service _service;

    public CreateCommand(Service service) {
        _service = service;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result;
        try {
            try {
                Gson gson = new Gson();
                Employee employee = gson.fromJson(request.getReader(), Employee.class);
                logger.info(employee.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }

            final GsonBuilder builder = new GsonBuilder();
            builder.excludeFieldsWithoutExposeAnnotation();
            final Gson gson = builder.create();

            result = gson.toJson(_service.getById(1));
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
