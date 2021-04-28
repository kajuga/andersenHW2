package com.kosshit.anderse.task2_3.jdbc;

import com.kosshit.anderse.task2_3.connection.PoolConnectionBuilder;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.EmployerLevel;
import com.kosshit.anderse.task2_3.model.EnglishLevel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EmployeeDAOTest {

    @Test
    public void createEmployeeTest() {
        EmployeeDAO dao = new EmployeeDAO();

        List<Connection> connectionPool = new ArrayList<>();
        dao.setConnectionBuilder(new PoolConnectionBuilder("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "root", connectionPool));

        Employee employee = new Employee();
        employee.setFirstName("Ivan");
        employee.setLastName("Ivanov");
        employee.setMiddleName("Ivanovich");
        employee.setShortName("I.I.I");
        employee.setEmail("ivanov@mail.com");
        employee.setPhoneNumber("2-12-90");
        employee.setBirthday(LocalDate.of(1994, 12, 20));
        employee.setDateOfStart(LocalDate.of(2021, 04, 26));
        employee.setEmpLevel(EmployerLevel.J1);
        employee.setEnglishLevel(EnglishLevel.A1);
        employee.setSkype("skype");

        boolean fact = dao.createEmployee(employee);
        log.debug("expected value true, actual value - {}", fact);
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void updateEmployee() {
    }

    @Test
    public void findAllEmployeesWithTeam() {
    }
}