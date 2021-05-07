package com.kosshit.anderse.task2_3.service;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.jdbc.TeamDAO;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.Project;
import com.kosshit.anderse.task2_3.model.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

public class TeamServiceTest {

    Team team;
    TeamDAO dao;
    TeamService service;

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
        Assert.assertTrue(service.create(team));
    }

    @Test
    public void getById() {
        Team teamFromDb = service.getById(1);
        Assert.assertNotNull(teamFromDb);
    }

    @Test
    public void update() {
        team.setTeamId(1);
        service.update(team);
        Assert.assertEquals("Putin".toLowerCase() , service.getById(1).getTeamName().toLowerCase());
    }

    @Test
    public void delete() {
        service.delete(2);
        Assert.assertNull(service.getById(2));
    }

    @Test
    public void getAll() {
        List<Team> list = service.getAll();
        Assert.assertNotNull(list);
    }
}
