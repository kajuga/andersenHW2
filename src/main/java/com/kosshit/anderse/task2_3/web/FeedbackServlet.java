package com.kosshit.anderse.task2_3.web;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.jdbc.FeedbackDAO;
import com.kosshit.anderse.task2_3.model.Feedback;
import com.kosshit.anderse.task2_3.model.Team;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class FeedbackServlet extends HttpServlet {

    private FeedbackDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new FeedbackDAO();
        try{
            dao.setConnectionBuilder(PoolConnectionBuilder.create("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");

        Feedback feedback = new Feedback();
        feedback.setFeedId(id.isEmpty() ? null : Integer.parseInt(id));
        feedback.setDescription(request.getParameter("description"));
        feedback.setDate(LocalDate.parse(request.getParameter("date")));

        if (feedback.isNew()) {
            dao.createFeedback(feedback);
        } else {
            dao.updateFeedback(feedback);
        }
        response.sendRedirect("feedback");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" :
                int id = getId(request);
                dao.deleteById(id);
                response.sendRedirect("feedback");
                break;
            case "create" :
                final Feedback create = new Feedback();
                request.setAttribute("fb", create);
                request.getRequestDispatcher("editFeedBack.jsp").forward(request, response);
                break;
            case "update" :
                final Feedback fb = dao.getFeedbackById(getId(request));
                request.setAttribute("fb", fb);
                request.getRequestDispatcher("editFeedBack.jsp").forward(request, response);
                break;
            case "all" :
            default:
                request.setAttribute("feedbacks", dao.getAllFeedback());
                request.getRequestDispatcher("feedback.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("feedbackId"));
        return Integer.parseInt(paramId);
    }

}
