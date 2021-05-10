package com.kosshit.anderse.task2_3.web;

import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.EmployerLevel;
import com.kosshit.anderse.task2_3.model.EnglishLevel;
import com.kosshit.anderse.task2_3.model.Team;
import com.kosshit.anderse.task2_3.web.controller.EmployeeController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class EmployeeServlet extends HttpServlet {

    private ClassPathXmlApplicationContext context;
    private EmployeeController controller;

    @Override
    public void init() throws ServletException {
        context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        controller = context.getBean(EmployeeController.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");

        Employee employee = new Employee();
        employee.setEmployeeId(id.isEmpty()? null : Integer.valueOf(id));
        employee.setFirstName(request.getParameter("firstName"));
        employee.setLastName(request.getParameter("lastName"));
        employee.setMiddleName(request.getParameter("middleName"));
        employee.setEmail(request.getParameter("email"));
        employee.setPhoneNumber(request.getParameter("phoneNumber"));
        employee.setBirthday(LocalDate.parse(request.getParameter("birthday")));
        employee.setDateOfStart(LocalDate.parse(request.getParameter("dateOfStart")));
        employee.setEmpLevel(EmployerLevel.valueOf(request.getParameter("devLevel")));
        employee.setEnglishLevel(EnglishLevel.valueOf(request.getParameter("englishLevel")));
        employee.setSkype(request.getParameter("skype"));
        Team team = new Team();
        team.setTeamId(Integer.valueOf(request.getParameter("teamId")));
        employee.setTeam(team);

        if (employee.isNew()) {
            controller.create(employee);
        } else {
            controller.update(employee);
        }

        response.sendRedirect("employee");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch(action == null ? "all" : action) {
            case "delete" :
                int id = getId(request);
                controller.delete(id);
                response.sendRedirect("employee");
                break;
            case "create" :
                final Employee create = new Employee();
                request.setAttribute("employee", create);
                request.getRequestDispatcher("editEmployee.jsp").forward(request, response);
            case "update" :
                final Employee employee = controller.getById(getId(request));
                request.setAttribute("employee", employee);
                request.getRequestDispatcher("editEmployee.jsp").forward(request, response);
                break;
            case "all" :
            default:
                request.setAttribute("employees", controller.getAll());
                request.getRequestDispatcher("employee.jsp").forward(request, response);
        }

    }

    @Override
    public void destroy() {
        context.close();
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("employeeId"));
        return Integer.parseInt(paramId);
    }
}
