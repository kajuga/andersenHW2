package com.kosshit.anderse.task2_3.web.controller;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.jdbc.TeamDAO;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.Team;
import com.kosshit.anderse.task2_3.service.TeamService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TeamControllerTest {

    Team team;
    TeamDAO dao;
    TeamService service;
    TeamController controller;

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

        service = new TeamService(dao);
        controller = new TeamController(service);

        Employee e1 = new Employee();
        e1.setEmployeeId(15);
        Employee e2 = new Employee();
        e1.setEmployeeId(16);
        List<Employee> employees = new  ArrayList<>(Arrays.asList(e1, e2));

        team = new Team();
        team.setTeamName("Putin");
        team.setEmployees(employees);
    }

    @Test
    public void create() {
        Assert.assertTrue(controller.create(team));
    }

    @Test
    public void getById() {
        Assert.assertNotNull(controller.getById(1));
    }

    @Test
    public void getAll() {
        List<Team> teams = controller.getAll();
        Assert.assertNotNull(teams);
        Assert.assertTrue(2 == teams.size());
    }

    @Test
    public void update() {
        team.setTeamId(1);
        controller.update(team);
        Assert.assertEquals("Putin", controller.getById(1).getTeamName());
    }

    @Test
    public void delete() {
        controller.delete(3);
        Assert.assertNull(controller.getById(3));
    }
}