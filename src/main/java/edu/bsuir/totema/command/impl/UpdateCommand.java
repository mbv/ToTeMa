package edu.bsuir.totema.command.impl;

import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.ResourceCommand;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.service.validation.ValidationResult;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.util.serialization.GsonProvider;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        HashMap<String, String> attributes = Command.getAttributesFromRequest(request);

        try {
            ValidationResult validationResult = _service.validate(attributes);
            if (validationResult != null) {
                response.setStatus(400);
                result = GsonProvider.getGson().toJson(validationResult);
            } else {
                result = GsonProvider.getGson().toJson(_service.update(_resource_id, attributes));

            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
