package edu.bsuir.totema.command.factory.service;

import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.command.factory.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This command called if {@link CommandFactory} cannot resolve command name (There is not match in )
 */
public class UnresolvedCommand implements Command {

    private static final Logger logger = Logger.getLogger(UnresolvedCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        logger.debug("Unresolved command has been executed.");
        /*request.setAttribute(ERROR_TITLE_ATTRIBUTE, ERROR_TITLE_UNRESOLVED);
        request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE_UNRESOLVED);
        String pagePath = PathManager.getProperty(PathManager.ERROR);*/
        Command.dispatchRequest("", false, request, response);
        return "{\"status\":\"error\"}";
    }
}
