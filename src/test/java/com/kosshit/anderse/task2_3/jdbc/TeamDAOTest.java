package com.kosshit.anderse.task2_3.jdbc;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.Project;
import com.kosshit.anderse.task2_3.model.Team;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class TeamDAOTest {

    Team team;
    TeamDAO dao;

    @Before
    public void setUp() {
        dao = new TeamDAO();
        List<Connection> connectionPool = new ArrayList<>();
        try {
            dao.setConnectionBuilder(PoolConnectionBuilder.create("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Employee e1 = new Employee();
        e1.setEmployeeId(15);
        Employee e2 = new Employee();
        e1.setEmployeeId(16);
        List<Employee> employees = new  ArrayList<>(Arrays.asList(e1, e2));

        team = new Team();
        team.setTeamName("Basket");
        team.setEmployees(employees);
    }

    @Test
    public void testCreateTeam() {
        boolean fact = dao.createTeam(team);

        Assert.assertTrue(fact);
        log.info("expected value true, actual value - {}", fact);
    }

    @Test
    public void testDeleteById() {
        dao.deleteById(3);
        Team fact  = dao.getTeamById(3);

        Assert.assertEquals(null, fact);
        log.info("expected value null, actual value - {}", fact);
    }

    @Test
    public void testGetTeamById() {
        Team t = dao.getTeamById(1);
        String fact = t.getTeamName();

        Assert.assertEquals("Shitikhin", fact);
        log.info("expected value Shitikhin, actual value - {}", fact );
    }

    @Test
    public void testGetAllTeams() {
        List<Team> list = dao.getAllTeams();

        int fact = list.size();

        Assert.assertEquals(2, fact);
        log.info("expected value '2', actual value - {}", fact );
    }

    @Test
    public void testUpdateTeam() {
        team.setTeamId(2);
        dao.updateTeam(team);
        Team t = dao.getTeamById(2);
        String fact = t.getTeamName();

        Assert.assertEquals("Basket", fact);
        log.info("expected value 'Basket', actual value - {}", fact );
    }
}