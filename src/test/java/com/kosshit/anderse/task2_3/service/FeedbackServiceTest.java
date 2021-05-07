package com.kosshit.anderse.task2_3.service;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.jdbc.FeedbackDAO;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.Feedback;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FeedbackServiceTest {

    Feedback feedback;
    FeedbackDAO dao;
    FeedbackService service;

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

        feedback = new Feedback();
        feedback.setDescription("broke");
        feedback.setDate(LocalDate.of(2021, 04, 29));
    }

    @Test
    public void create() {
        Assert.assertTrue(service.create(feedback));
    }

    @Test
    public void getById() {
        Feedback feedFromDb = service.getById(1);
        Assert.assertNotNull(feedFromDb);
    }

    @Test
    public void update() {
        feedback.setFeedId(1);
        service.update(feedback);
        Assert.assertEquals("broke" , service.getById(1).getDescription());
    }

    @Test
    public void delete() {
        service.delete(2);
        Assert.assertNull(service.getById(2));
    }

    @Test
    public void getAll() {
        List<Feedback> list = service.getAll();
        Assert.assertNotNull(list);
    }
}