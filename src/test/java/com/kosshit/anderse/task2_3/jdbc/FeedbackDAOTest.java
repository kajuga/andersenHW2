package com.kosshit.anderse.task2_3.jdbc;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.Feedback;
import com.kosshit.anderse.task2_3.model.Team;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class FeedbackDAOTest {

    Feedback feedback;
    FeedbackDAO dao;

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

        feedback = new Feedback();
        feedback.setDescription("broke");
        feedback.setDate(LocalDate.of(2021, 04, 29));
    }

    @Test
    public void testCreateFeedback() {
        boolean fact = dao.createFeedback(feedback);

        Assert.assertTrue(fact);
        log.info("expected value true, actual value - {}", fact);
    }

    @Test
    public void testDeleteById() {
        dao.deleteById(8);
        Feedback fact  = dao.getFeedbackById(8);

        Assert.assertEquals(null, fact);
        log.info("expected value null, actual value - {}", fact);
    }

    @Test
    public void testGetAllFeedback() {
        List<Feedback> list = dao.getAllFeedback();

        int fact = list.size();

        Assert.assertEquals(6, fact);
        log.info("expected value '6', actual value - {}", fact );
    }

    @Test
    public void testGetFeedbackById() {
        Feedback f = dao.getFeedbackById(7);
        String fact = f.getDescription();

        Assert.assertEquals("broke", fact);
        log.info("expected value 'broke', actual value - {}", fact );
    }

    @Test
    public void testUpdateFeedback() {
        feedback.setFeedId(7);
        dao.updateFeedback(feedback);
        Feedback f = dao.getFeedbackById(7);
        String fact = f.getDescription();

        Assert.assertEquals("broke", fact);
        log.info("expected value 'broke', actual value - {}", fact );
    }
}