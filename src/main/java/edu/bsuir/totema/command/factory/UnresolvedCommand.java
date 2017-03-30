package edu.bsuir.totema.command.factory;

import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.exception.CommandException;
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
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return "{\"error\":\"Unresolved command has been executed\"}";
    }
}
