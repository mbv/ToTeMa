package edu.bsuir.totema.command.impl;

import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.response.ResponseErrorInfo;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.exception.ServiceException;
import edu.bsuir.totema.util.serialization.GsonProvider;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        HashMap<String, String> attributes = Command.getAttributesFromRequest(request);
        try {
            ResponseErrorInfo responseErrorInfo = _service.validate(attributes);
            if (responseErrorInfo != null) {
                response.setStatus(400);
                result = GsonProvider.getGson().toJson(responseErrorInfo);
            } else {
                result = GsonProvider.getGson().toJson(_service.add(attributes));

            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
