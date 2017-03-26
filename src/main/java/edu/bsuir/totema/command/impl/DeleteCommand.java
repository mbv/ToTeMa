package edu.bsuir.totema.command.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.bsuir.totema.command.ResourceCommand;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class DeleteCommand implements ResourceCommand {
    private static final Logger logger = Logger.getLogger(ViewAllCommand.class);
    private Service _service;
    private long _resource_id;

    public DeleteCommand(Service service, long resource_id) {
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

            HashMap<String,String> resultMap = new HashMap<>();
            if (_service.delete(_resource_id)) {
                resultMap.put("status", "good");
            } else {
                resultMap.put("status", "error");
            }

            result = gson.toJson(resultMap);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}
