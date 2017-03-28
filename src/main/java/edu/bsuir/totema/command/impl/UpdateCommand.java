package edu.bsuir.totema.command.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.bsuir.totema.command.ResourceCommand;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.service.validation.ValidationResult;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class UpdateCommand implements ResourceCommand {
    private static final Logger logger = Logger.getLogger(ViewAllCommand.class);
    private Service _service;
    private long _resource_id;

    public UpdateCommand(Service service, long resource_id) {
        _service = service;
        _resource_id = resource_id;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String result;
        HashMap<String, String> attributes;
        try {
            final Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, String>>(){}.getType();
            attributes = gson.fromJson(request.getReader(), type);
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
                result = gson.toJson(validationResult);
            } else {
                result = gson.toJson(_service.update(_resource_id, attributes));

            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
