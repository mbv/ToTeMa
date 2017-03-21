package edu.bsuir.totema.command.factory.service;

import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.service.Service;
import org.apache.log4j.Logger;

public class UnresolvedServiceCommandFactory extends ServiceCommandFactory {

    private static final Logger logger = Logger.getLogger(UnresolvedCommand.class);

    @Override
    public Command defineCommand(String requestMethod, String commandType, Integer resourceId) {
        logger.debug("Unresolved command has been executed.");
        return new UnresolvedCommand();
    }

    @Override
    Service getService() {
        return null;
    }
}
