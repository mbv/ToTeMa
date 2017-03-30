package edu.bsuir.totema.command;

import com.google.gson.reflect.TypeToken;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.util.serialization.GsonProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Main interface of Command pattern which used to delegate
 * every web app action from controller to separated amount of classes,
 * flexible and extendable
 */
public interface Command {

    /**
     * Default dispatch for request
     *
     * @param pagePath path of page to forward
     * @param isAsync  if {@code true}, exception handling will be for async requests
     * @param request  request to dispatch
     * @param response response to dispatch
     * @throws CommandException if cannot dispatch request for specified {@code pagePath}
     */
    static void dispatchRequest(String pagePath, boolean isAsync, HttpServletRequest request,
                                          HttpServletResponse response) throws CommandException {
        try {
            request.getRequestDispatcher(pagePath).forward(request, response);
        } catch (ServletException e) {
            throw new CommandException("Servlet exception occurs. ", e, isAsync);
        } catch (IOException e) {
            throw new CommandException("IOException exception occurs. ", e, isAsync);
        }
    }

    /**
     * Default redirect for the specified url
     *
     * @param url      url to redirect to
     * @param response response for redirecting
     * @throws CommandException if internal error occurs
     */
    static void sendRedirect(String url, HttpServletResponse response) throws CommandException {
        try {
            url = response.encodeRedirectURL(url);
            response.sendRedirect(url);
        } catch (IOException e) {
            throw new CommandException("IOException exception occurs. ", e, false);
        }
    }
    static HashMap<String, String> getAttributesFromRequest(HttpServletRequest request) throws CommandException {
        HashMap<String, String> attributes;
        try {
            Type type = new TypeToken<HashMap<String, String>>(){}.getType();
            attributes = GsonProvider.getGson().fromJson(request.getReader(), type);
        } catch (IOException e) {
            throw new CommandException(e);
        }
        if (attributes == null) {
            attributes = new HashMap<>();
        }
        return attributes;
    }

    /**
     * Executes command using {@link HttpServletRequest} specified
     *
     * @param request request from the user with necessary data to execute
     * @param response response to the user for future actions
     * @throws CommandException if command cannot be executed or execution failed
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;

}