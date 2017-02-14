package by.totema.controller;

import by.totema.entity.Employee;
import by.totema.model.Model;
import by.totema.model.ModelException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "Servlet", urlPatterns = {"/controller"})
public class Controller extends javax.servlet.http.HttpServlet {


    private static final String EMPLOYEE_LIST_PAGE_PATH = "/WEB-INF/jsp/employee_list.jsp";
    private static final String EMPLOYEES_ATTRIBUTE = "employees";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("Hello Word");*/
        try {
            List<Employee> employees = Model.getInstance().selectAllUsers();
            request.setAttribute(EMPLOYEES_ATTRIBUTE, employees);
            request.getRequestDispatcher(EMPLOYEE_LIST_PAGE_PATH).forward(request, response);
        } catch (ModelException e) {
            e.printStackTrace();
            response.sendError(404);
        }
    }
}
