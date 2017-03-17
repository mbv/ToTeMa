package edu.bsuir.totema.command.factory.service;

import edu.bsuir.totema.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mbv on 3/16/17.
 */
public class UnresolvedServiceCommandFactory implements ServiceCommandFactory {
    @Override
    public Command defineCommand(HttpServletRequest request) {
        return null;
    }
}
