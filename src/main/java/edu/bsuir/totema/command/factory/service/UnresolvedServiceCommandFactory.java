package edu.bsuir.totema.command.factory.service;

import edu.bsuir.totema.command.Command;
import org.apache.log4j.Logger;

public class UnresolvedServiceCommandFactory implements ServiceCommandFactory {

    private static final Logger logger = Logger.getLogger(UnresolvedCommand.class);

    @Override
    public Command defineCommand(String requestMethod, String commandType) {
        logger.debug("Unresolved command has been executed.");
        return new UnresolvedCommand();
    }
}
