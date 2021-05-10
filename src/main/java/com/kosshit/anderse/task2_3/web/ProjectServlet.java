package com.kosshit.anderse.task2_3.web;

import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.Project;
import com.kosshit.anderse.task2_3.model.Team;
import com.kosshit.anderse.task2_3.web.controller.ProjectController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class ProjectServlet extends HttpServlet {

    private ClassPathXmlApplicationContext context;
    private ProjectController controller;

    @Override
    public void init() throws ServletException {
      context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
      controller = context.getBean(ProjectController.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");

        Project project = new Project();
        project.setProjectId(id.isEmpty()? null : Integer.parseInt(id));
        project.setNameOfProject(request.getParameter("name"));
        project.setCustomer(request.getParameter("customer"));
        project.setDuration(request.getParameter("duration"));
        project.setMethodology(request.getParameter("methodology"));
        Team team = new Team();
        team.setTeamId(Integer.parseInt(request.getParameter("teamId")));
        project.setTeam(team);
        Employee employee = new Employee();
        employee.setEmployeeId(Integer.parseInt(request.getParameter("projectManagerId")));
        project.setProjectManager(employee);

        if (project.isNew()) {
            controller.create(project);
        } else {
            controller.update(project);
        }

        response.sendRedirect("project");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        switch(action == null ? "all" : action) {
            case "delete" :
                int id = getId(request);
                controller.delete(id);
                response.sendRedirect("project");
                break;
            case "create" :
                final Project create = new Project();
                request.setAttribute("project", create);
                request.getRequestDispatcher("editProject.jsp").forward(request, response);
                break;
            case "update" :
                final Project project = controller.getById(getId(request));
                request.setAttribute("project", project);
                request.getRequestDispatcher("editProject.jsp").forward(request, response);
                break;
            case "all" :
            default:
                request.setAttribute("projects", controller.getAll());
                request.getRequestDispatcher("project.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        context.close();
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("projectId"));
        return Integer.parseInt(paramId);
    }
}
