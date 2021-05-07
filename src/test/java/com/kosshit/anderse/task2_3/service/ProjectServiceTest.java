package com.kosshit.anderse.task2_3.service;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.jdbc.ProjectDAO;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.Feedback;
import com.kosshit.anderse.task2_3.model.Project;
import com.kosshit.anderse.task2_3.model.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProjectServiceTest {

    Project project;
    ProjectDAO dao;
    ProjectService service;

    @Before
    public void setUp() {
        dao = new ProjectDAO();
        List<Connection> connectionPool = new ArrayList<>();
        try {
            dao.setConnectionBuilder(PoolConnectionBuilder.create("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        service = new ProjectService(dao);

        Team team = new Team();
        team.setTeamId(1);

        Employee employee = new Employee();
        employee.setEmployeeId(2);

        project = new Project();
        project.setNameOfProject("Basket");
        project.setCustomer("FBR");
        project.setDuration("...");
        project.setMethodology("chih chih");
        project.setTeam(team);
        project.setProjectManager(employee);

    }

    @Test
    public void create() {
        Assert.assertTrue(service.create(project));
    }

    @Test
    public void getById() {
        Project projectFromDb = service.getById(1);
        Assert.assertNotNull(projectFromDb);
    }

    @Test
    public void update() {
        project.setProjectId(1);
        service.update(project);
        Assert.assertEquals("FBR".toLowerCase() , service.getById(1).getCustomer().toLowerCase());
    }

    @Test
    public void delete() {
        service.delete(2);
        Assert.assertNull(service.getById(2));
    }

    @Test
    public void getAll() {
        List<Project> list = service.getAll();
        Assert.assertNotNull(list);
    }
}