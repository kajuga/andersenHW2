package com.kosshit.anderse.task2_3.web;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.jdbc.TeamDAO;
import com.kosshit.anderse.task2_3.model.Team;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class TeamServlet extends HttpServlet {

    private TeamDAO dao;

    @Override
    public void init() throws ServletException {
        dao= new TeamDAO();
        try {
            dao.setConnectionBuilder(PoolConnectionBuilder.create("jdbc:postgresql://localhost:5432/postgres", "postgres", "root"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");

        Team team = new Team();
        team.setTeamId(id.isEmpty() ? null : Integer.parseInt(request.getParameter("id")));
        team.setTeamName(request.getParameter("name"));

        if (team.isNew()) {
            dao.createTeam(team);
        } else {
            dao.updateTeam(team);
        }
        response.sendRedirect("team");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" :
                int id = getId(request);
                dao.deleteById(id);
                response.sendRedirect("team");
                break;
            case "create" :
               final Team create = new Team();
                request.setAttribute("team", create);
                request.getRequestDispatcher("editTeam.jsp").forward(request, response);
                break;
            case "update" :
                final Team team = dao.getTeamById(getId(request));
                request.setAttribute("team", team);
                request.getRequestDispatcher("editTeam.jsp").forward(request, response);
                break;
            case "all" :
            default:
                request.setAttribute("teams", dao.getAllTeams());
                request.getRequestDispatcher("team.jsp").forward(request, response);
        }

    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("teamId"));
        return Integer.parseInt(paramId);
    }
}
