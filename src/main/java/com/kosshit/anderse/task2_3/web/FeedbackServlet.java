package com.kosshit.anderse.task2_3.web;

import com.kosshit.anderse.task2_3.model.Feedback;
import com.kosshit.anderse.task2_3.web.controller.FeedbackController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class FeedbackServlet extends HttpServlet {

    private ClassPathXmlApplicationContext context;
    private FeedbackController controller;

    @Override
    public void init() throws ServletException {
        context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        controller = context.getBean(FeedbackController.class);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");

        Feedback feedback = new Feedback();
        feedback.setFeedId(id.isEmpty() ? null : Integer.parseInt(id));
        feedback.setDescription(request.getParameter("description"));
        feedback.setDate(LocalDate.parse(request.getParameter("date")));

        if (feedback.isNew()) {
            controller.create(feedback);
        } else {
            controller.update(feedback);
        }
        response.sendRedirect("feedback");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" :
                int id = getId(request);
                controller.delete(id);
                response.sendRedirect("feedback");
                break;
            case "create" :
                final Feedback create = new Feedback();
                request.setAttribute("fb", create);
                request.getRequestDispatcher("editFeedBack.jsp").forward(request, response);
                break;
            case "update" :
                final Feedback fb = controller.getById(getId(request));
                request.setAttribute("fb", fb);
                request.getRequestDispatcher("editFeedBack.jsp").forward(request, response);
                break;
            case "all" :
            default:
                request.setAttribute("feedbacks", controller.getAll());
                request.getRequestDispatcher("feedback.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        context.close();
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("feedbackId"));
        return Integer.parseInt(paramId);
    }

}
