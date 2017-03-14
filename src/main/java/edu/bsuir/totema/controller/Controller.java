package edu.bsuir.totema.controller;

import edu.bsuir.totema.entity.Employee;
import edu.bsuir.totema.model.Model;
import edu.bsuir.totema.model.ModelException;

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
        try {
            List<Employee> employees = Model.getInstance().selectAllUsers();
            request.setAttribute(EMPLOYEES_ATTRIBUTE, employees);
            request.getRequestDispatcher(EMPLOYEE_LIST_PAGE_PATH).forward(request, response);
        } catch (ModelException e) {
            throw new ServletException(e);
        }
    }
}
