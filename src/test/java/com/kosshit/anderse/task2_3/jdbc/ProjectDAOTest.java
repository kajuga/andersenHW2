package com.kosshit.anderse.task2_3.jdbc;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.Project;
import com.kosshit.anderse.task2_3.model.Team;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProjectDAOTest extends TestCase {

    Project project;
    ProjectDAO dao;

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
    public void testCreateProject() {
        boolean fact = dao.createProject(project);

        Assert.assertTrue(fact);
        log.info("expected value true, actual value - {}", fact);
    }

    @Test
    public void testDeleteById() {
        dao.deleteById(3);
        Project fact  = dao.getProjectById(3);

        Assert.assertEquals(null, fact);
        log.info("expected value null, actual value - {}", fact);
    }

    @Test
    public void testGetProjectById() {
        Project p = dao.getProjectById(2);
        String fact = p.getCustomer();

        Assert.assertEquals("RFS", fact);
        log.info("expected value RFS, actual value - {}", fact );
    }

    @Test
    public void testUpdateProject() {
        project.setProjectId(1);
        dao.updateProject(project);
        Project e = dao.getProjectById(1);
        String fact = e.getDuration();

        Assert.assertEquals("...", fact);
        log.info("expected value '...', actual value - {}", fact );
    }

    @Test
    public void testGetAllProjects() {
        List<Project> list = dao.getAllProjects();

        int fact = list.size();

        Assert.assertEquals(2, fact);
        log.info("expected value '2', actual value - {}", fact );
    }
}