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
        Command command = CommandFactory.defineCommand(request).defineCommand(request);
        try {
            logger.info(request.getMethod());
            response.setContentType("text/json; charset=UTF-8");
            PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"), true);

            String result = command.execute(request, response);

            out.println(result);
            out.close();
        } catch (CommandException e) {
            logger.error("Cannot execute command.\n", e);
            String pagePath = "";
            if (e.isAsync()) {
              //  request.setAttribute(AJAX_IS_RESULT_SUCCESS_ATTRIBUTE, false);
                //pagePath = PathManager.getProperty(PathManager.AJAX_RESPONSE);
            } else {
               // pagePath = PathManager.getProperty(PathManager.ERROR);
            }
            //request.setAttribute(ERROR_TITLE_ATTRIBUTE, ERROR_TITLE_COMMAND);
           // request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, ERROR_MESSAGE_COMMAND);
            //request.getRequestDispatcher(pagePath).forward(request, response);
        }
    }

    /*private class RestRequest {
        // Accommodate two requests, one for all resources, another for a specific resource
        private Pattern regExAllPattern = Pattern.compile("/users");
        private Pattern regExIdPattern = Pattern.compile("/users/([0-9]*)");

        private Integer id;

        public RestRequest(String pathInfo) throws ServletException {
            // regex parse pathInfo
            Matcher matcher;

            // Check for ID case first, since the All pattern would also match
            matcher = regExIdPattern.matcher(pathInfo);
            if (matcher.find()) {
                id = Integer.parseInt(matcher.group(1));
                return;
            }

            matcher = regExAllPattern.matcher(pathInfo);
            if (matcher.find()) return;

            throw new ServletException("Invalid URI");
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"), true);
        response.setContentType("text/json; charset=UTF-8");
        rrequest.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        out.println("GET request handling");
        out.println(request.getPathInfo());
        out.println(request.getParameterMap());
        try {
            RestRequest resourceValues = new RestRequest(request.getPathInfo());
            ArrayList<Employee> employees = new ArrayList<>();
            Employee employee = new Employee();
            employee.setYearSalary(100);
            employee.setTitle("Test");
            employee.setName("Тест");
            employees.add(employee);
            employees.add(employee);

            Gson gson = new Gson();
            String json = gson.toJson(employees);

            out.println(json);
        } catch (ServletException e) {
            response.setStatus(400);
            response.resetBuffer();
            e.printStackTrace();
            out.println(e.toString());
        }
        out.close();
    }*/

}
