package com.kosshit.anderse.task2_3.web.controller;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.jdbc.FeedbackDAO;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.Feedback;
import com.kosshit.anderse.task2_3.service.FeedbackService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FeedbackControllerTest {

    Feedback feedback;
    FeedbackDAO dao;
    FeedbackService service;
    FeedbackController controller;

    @Before
    public void setUp() {
        dao = new FeedbackDAO();
        List<Connection> connectionPool = new ArrayList<>();
        try {
            dao.setConnectionBuilder(PoolConnectionBuilder.create("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        service = new FeedbackService(dao);
        controller = new FeedbackController(service);

        feedback = new Feedback();
        feedback.setDescription("broke");
        feedback.setDate(LocalDate.of(2021, 04, 29));
    }

    @Test
    public void create() {
        Assert.assertTrue(controller.create(feedback));
    }

    @Test
    public void getById() {
        Assert.assertNotNull(controller.getById(1));
    }

    @Test
    public void getAll() {
        List<Feedback> feedbacks = controller.getAll();
        Assert.assertNotNull(feedbacks);
        Assert.assertTrue(7 == feedbacks.size());
    }

    @Test
    public void update() {
        feedback.setFeedId(1);
        controller.update(feedback);
        Assert.assertEquals("broke", controller.getById(1).getDescription());
    }

    @Test
    public void delete() {
        controller.delete(8);
        Assert.assertNull(controller.getById(8));
    }
}