package com.kosshit.anderse.task2_3.web;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.jdbc.ProjectDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

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

    //    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("projects", dao.getAllProjects());

        request.getRequestDispatcher("project.jsp").forward(request, response);
    }
}
