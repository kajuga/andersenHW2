package com.kosshit.anderse.task2_3.web.controller;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.jdbc.ProjectDAO;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.Project;
import com.kosshit.anderse.task2_3.model.Team;
import com.kosshit.anderse.task2_3.service.ProjectService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProjectControllerTest {

    Project project;
    ProjectDAO dao;
    ProjectService service;
    ProjectController controller;

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
        controller = new ProjectController(service);

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
        Assert.assertTrue(controller.create(project));
    }

    @Test
    public void getById() {
        Assert.assertNotNull(controller.getById(1));
    }

    @Test
    public void getAll() {
        List<Project> projects = controller.getAll();
        Assert.assertNotNull(projects);
        Assert.assertTrue(2 == projects.size());
    }

    @Test
    public void update() {
        project.setProjectId(1);
        controller.update(project);
        Assert.assertEquals("FBR", controller.getById(1).getCustomer());
    }

    @Test
    public void delete() {
        controller.delete(3);
        Assert.assertNull(controller.getById(3));
    }
}