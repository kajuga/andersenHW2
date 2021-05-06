package com.kosshit.anderse.task2_3.web;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.jdbc.ProjectDAO;
import com.kosshit.anderse.task2_3.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class ProjectServlet extends HttpServlet {

    private ProjectDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new ProjectDAO();
        try {
            dao.setConnectionBuilder(PoolConnectionBuilder.create("jdbc:postgresql://localhost:5432/postgres", "postgres", "root"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
//        Team team = new Team();
//        team.setTeamId(Integer.parseInt(request.getParameter("teamId")));
//        project.setTeam(team);
//        Employee employee = new Employee();
//        employee.setEmployeeId(Integer.parseInt(request.getParameter("managerId")));
//        project.setProjectManager(employee);

        if (project.isNew()) {
            dao.createProject(project);
        } else {
            dao.updateProject(project);
        }

        response.sendRedirect("project");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        switch(action == null ? "all" : action) {
            case "delete" :
                int id = getId(request);
                dao.deleteById(id);
                response.sendRedirect("project");
                break;
            case "create" :
                final Project create = new Project();
                request.setAttribute("project", create);
                request.getRequestDispatcher("editProject.jsp").forward(request, response);
                break;
            case "update" :
                final Project project = dao.getProjectById(getId(request));
                request.setAttribute("project", project);
                request.getRequestDispatcher("editProject.jsp").forward(request, response);
                break;
            case "all" :
            default:
                request.setAttribute("projects", dao.getAllProjects());
                request.getRequestDispatcher("project.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("projectId"));
        return Integer.parseInt(paramId);
    }
}
