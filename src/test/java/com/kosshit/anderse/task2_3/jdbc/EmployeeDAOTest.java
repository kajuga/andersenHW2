package com.kosshit.anderse.task2_3.jdbc;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.EmployerLevel;
import com.kosshit.anderse.task2_3.model.EnglishLevel;
import com.kosshit.anderse.task2_3.model.Team;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EmployeeDAOTest {

    Employee employee;
    EmployeeDAO dao;

    @Before
    public void setUp() {
        dao = new EmployeeDAO();
        List<Connection> connectionPool = new ArrayList<>();
        try {
            dao.setConnectionBuilder(PoolConnectionBuilder.create("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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
    public void testCreateEmployeeTest() {

        boolean fact = dao.createEmployee(employee);
        log.info("expected value true, actual value - {}", fact);
    }

    @Test
    public void testDeleteByIdTest() {
        dao.deleteById(3);
        Employee fact  = dao.getEmployeeById(3);


        log.info("expected value null, actual value - {}", fact);
    }

    @Test
    public void testUpdateEmployeeTest() {
        employee.setEmployeeId(4);
        dao.updateEmployee(employee);
        Employee e = dao.getEmployeeById(4);
        String fact = e.getFirstName();

        log.info("expected value Ivan, actual value - {}", fact );
    }


    @Test
    public void testGetEmployeeByIdTest() {
       Employee e = dao.getEmployeeById(7);
       String fact = e.getLastName();
       log.info("expected value Crosby, actual value - {}", fact );
    }
}