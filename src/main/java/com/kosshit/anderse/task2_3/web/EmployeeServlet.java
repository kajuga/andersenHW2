package com.kosshit.anderse.task2_3.web;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.jdbc.EmployeeDAO;
import com.kosshit.anderse.task2_3.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class EmployeeServlet extends HttpServlet {

   private EmployeeDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new EmployeeDAO();
        try {
            dao.setConnectionBuilder(PoolConnectionBuilder.create("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("employeeId");

        Employee employee = new Employee(id.isEmpty() ? null : Integer.valueOf(id),
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("middleName"),
                request.getParameter("email"),
                request.getParameter("phoneNumber"),
                LocalDate.parse(request.getParameter("birthday")),
                LocalDate.parse(request.getParameter("dateOfStart")),
                new Project(),
                EmployerLevel.valueOf(request.getParameter("devLevel")),
                EnglishLevel.valueOf(request.getParameter("englishLevel")),
                request.getParameter("skype"),
                new Feedback(),
                new Team());

        if (id.isEmpty()) {
            dao.createEmployee(employee);
        } else {
            dao.updateEmployee(employee);
        }
        response.sendRedirect("employee");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch(action == null ? "all" : action) {
            case "delete" :
                int id = getId(request);
                dao.deleteById(id);
                response.sendRedirect("employee");
                break;
            case "create" :
            case "update" :
                final Employee employee = "create".equals(action) ?
                        new Employee(null, "Ivanov", "Ivan", "Ivanovich") : dao.getEmployeeById(getId(request));
                request.setAttribute("employee", employee);
                request.getRequestDispatcher("formEmployee.jsp").forward(request, response);
                break;
            case "all" :
            default:
                request.setAttribute("employee", dao.getAllEmployees());
                request.getRequestDispatcher("employee.jsp").forward(request, response);
        }

    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("employeeId"));
        return Integer.parseInt(paramId);
    }
}
