package edu.bsuir.totema.controller;

import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.exception.CommandException;
import edu.bsuir.totema.command.factory.CommandFactory;
import edu.bsuir.totema.dao.pool.ConnectionPool;
import edu.bsuir.totema.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.bsuir.totema.command.resource.Constants.COMMAND_TYPE_ALL;
import static edu.bsuir.totema.command.resource.Constants.COMMAND_TYPE_RESOURCE;


public class FrontController extends javax.servlet.http.HttpServlet {
    private static final Logger logger = Logger.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        RestRequestParser resourceValues = new RestRequestParser(request.getPathInfo());
        Command command = CommandFactory.defineCommand(resourceValues.getService()).defineCommand(request.getMethod(), resourceValues.getType());
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

    private class RestRequestParser {
        private String serviceListString;
        private Pattern regExAllPattern;
        private Pattern regExIdPattern;

        private String service;
        private Integer id;
        private String type;

        {
            StringBuilder result = new StringBuilder();
            result.append("(");
            boolean isFirst = true;
            for (String service : ServiceFactory.getServices()) {
                if (!isFirst) {
                    result.append("|");
                }
                result.append(service);
                isFirst = false;
            }
            result.append(")");

            serviceListString = result.toString();
            regExAllPattern = Pattern.compile("/"+serviceListString);
            regExIdPattern = Pattern.compile("/"+serviceListString+"/([0-9]*)");
        }
        public RestRequestParser(String pathInfo) throws ServletException {
            Matcher matcher;

            matcher = regExIdPattern.matcher(pathInfo);
            if (matcher.find()) {
                service = matcher.group(1);
                id = Integer.parseInt(matcher.group(2));
                type = COMMAND_TYPE_RESOURCE;
                return;
            }

            matcher = regExAllPattern.matcher(pathInfo);
            if (matcher.find()) {
                service = matcher.group(1);
                type = COMMAND_TYPE_ALL;
                return;
            }

            //throw new ServletException("Invalid URI");
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}
