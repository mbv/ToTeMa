package edu.bsuir.totema.command.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.service.validation.ValidationResult;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class CreateCommand implements Command {

    private static final Logger logger = Logger.getLogger(ViewAllCommand.class);
    private Service _service;

    public CreateCommand(Service service) {
        _service = service;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result;
        HashMap<String, String> attributes = new HashMap<>();
        try {
            final Gson gson = new Gson();

            attributes = gson.fromJson(request.getReader(), attributes.getClass());
        } catch (IOException e) {
            throw new CommandException(e);
        }
        if (attributes == null) {
            attributes = new HashMap<>();
        }
        try {
            final GsonBuilder builder = new GsonBuilder();
            builder.excludeFieldsWithoutExposeAnnotation();
            final Gson gson = builder.create();

            ValidationResult validationResult = _service.validate(attributes);
            if (validationResult != null) {
                response.setStatus(400);
                result = gson.toJson(validationResult);
            } else {
                result = gson.toJson(_service.add(attributes));

            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
