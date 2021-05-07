package com.kosshit.anderse.task2_3.web.controller;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.jdbc.EmployeeDAO;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.EmployerLevel;
import com.kosshit.anderse.task2_3.model.EnglishLevel;
import com.kosshit.anderse.task2_3.model.Team;
import com.kosshit.anderse.task2_3.service.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeControllerTest {

    Employee employee;
    EmployeeDAO dao;
    EmployeeService service;
    EmployeeController controller;

    @Before
    public void setUp() {
        dao = new EmployeeDAO();
        service = new EmployeeService(dao);
        try {
            dao.setConnectionBuilder(PoolConnectionBuilder.create("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        service = new EmployeeService(dao);
        controller = new EmployeeController(service);

        employee = new Employee();
        employee.setFirstName("Ivan");
        employee.setLastName("Ivanov");
        employee.setMiddleName("Ivanovich");
        employee.setShortName("I.I.I");
        employee.setEmail("ivanov@mail.com");
        employee.setPhoneNumber("+7-911-258-58-50");
        employee.setBirthday(LocalDate.of(1994, 12, 20));
        employee.setDateOfStart(LocalDate.of(2021, 04, 26));
        employee.setEmpLevel(EmployerLevel.J1);
        employee.setEnglishLevel(EnglishLevel.A1);
        employee.setSkype("skype");

        Team team = new Team();
        team.setTeamId(1);
        employee.setTeam(team);
    }


    @Test
    public void create() {
        Assert.assertTrue(controller.create(employee));
    }

    @Test
    public void getById() {
        Assert.assertNotNull(controller.getById(1));
    }

    @Test
    public void getAll() {
        List<Employee> employees = controller.getAll();
        Assert.assertNotNull(employees);
        Assert.assertTrue(12 == employees.size());
    }

    @Test
    public void update() {
        employee.setEmployeeId(1);
        controller.update(employee);
        Assert.assertEquals("Ivan", controller.getById(1).getFirstName());
    }

    @Test
    public void delete() {
        controller.delete(13);
        Assert.assertNull(controller.getById(13));
    }
}