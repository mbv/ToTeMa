package edu.bsuir.totema.command.factory.service;

import edu.bsuir.totema.command.Command;

import javax.servlet.http.HttpServletRequest;

public interface ServiceCommandFactory {
    Command defineCommand(String requestMethod, String commandType);
}
