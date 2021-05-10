package com.kosshit.anderse.task2_3.web;

import com.kosshit.anderse.task2_3.model.Team;
import com.kosshit.anderse.task2_3.web.controller.TeamController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class TeamServlet extends HttpServlet {

    private ClassPathXmlApplicationContext context;
    private TeamController controller;

    @Override
    public void init() throws ServletException {
      context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
      controller = context.getBean(TeamController.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");

        Team team = new Team();
        team.setTeamId(id.isEmpty() ? null : Integer.parseInt(request.getParameter("id")));
        team.setTeamName(request.getParameter("name"));

        if (team.isNew()) {
            controller.create(team);
        } else {
            controller.update(team);
        }
        response.sendRedirect("team");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" :
                int id = getId(request);
                controller.delete(id);
                response.sendRedirect("team");
                break;
            case "create" :
               final Team create = new Team();
                request.setAttribute("team", create);
                request.getRequestDispatcher("editTeam.jsp").forward(request, response);
                break;
            case "update" :
                final Team team = controller.getById(getId(request));
                request.setAttribute("team", team);
                request.getRequestDispatcher("editTeam.jsp").forward(request, response);
                break;
            case "all" :
            default:
                request.setAttribute("teams", controller.getAll());
                request.getRequestDispatcher("team.jsp").forward(request, response);
        }

    }

    @Override
    public void destroy() {
        context.close();
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("teamId"));
        return Integer.parseInt(paramId);
    }
}
