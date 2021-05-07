package com.kosshit.anderse.task2_3.service;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.jdbc.EmployeeDAO;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.EmployerLevel;
import com.kosshit.anderse.task2_3.model.EnglishLevel;
import com.kosshit.anderse.task2_3.model.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class EmployeeServiceTest {

    Employee employee;
    EmployeeDAO dao;
    EmployeeService service;

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
        Assert.assertTrue(service.create(employee));
    }

    @Test
    public void getById() {
        Employee employeeFromDb = service.getById(1);
        Assert.assertNotNull(employeeFromDb);
    }

    @Test
    public void update() {
        employee.setEmployeeId(1);
        service.update(employee);
        Assert.assertEquals("Ivan" , service.getById(1).getFirstName());
    }

    @Test
    public void delete() {
        service.delete(1);
        Assert.assertNull(service.getById(1));
    }

    @Test
    public void getAll() {
        List<Employee> list = service.getAll();
        Assert.assertNotNull(list);
    }

}