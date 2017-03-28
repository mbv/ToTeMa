package edu.bsuir.totema.controller;

import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.command.factory.CommandFactory;
import edu.bsuir.totema.dao.pool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class FrontController extends javax.servlet.http.HttpServlet {
    private static final Logger logger = Logger.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().releasePool();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = CommandFactory.defineCommand(request.getPathInfo(), request.getMethod());
        try {
            logger.info(request.getMethod());
            response.setContentType("text/json; charset=UTF-8");
            PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"), true);

            String result = command.execute(request, response);

            out.print(result);
            out.close();
        } catch (CommandException e) {
            logger.error("Cannot execute command.\n", e);
        }
    }

}
