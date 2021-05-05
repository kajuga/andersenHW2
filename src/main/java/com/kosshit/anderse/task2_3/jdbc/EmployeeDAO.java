package com.kosshit.anderse.task2_3.jdbc;

import com.kosshit.anderse.task2_3.connection.ConnectionBuilder;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.Project;
import com.kosshit.anderse.task2_3.model.Team;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EmployeeDAO {

    private static final String INSERT_EMPLOYEE =
            "INSERT INTO employee (first_name, last_name, middle_name, short_name, " +
                    "email, phone_number, birthday, date_of_start, dev_level, english_level, skype, team_id)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING employee_id";

    private static final String DELETE_EMPLOYEE =
            "DELETE FROM employee WHERE employee_id = ? RETURNING employee_id";

    private static final String UPDATE_EMPLOYEE =
            "UPDATE employee SET " +
                    " first_name = ?, last_name = ?, middle_name = ?, short_name = ?, email = ?, phone_number = ?, birthday = ?, date_of_start = ?, dev_level = ?, english_level = ?, skype = ?, team_id = ? " +
                    " WHERE employee_id = ? RETURNING employee_id";


    private static final String GET_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE employee_id = ?";

    private static final String GET_ALL_EMPLOYEE = "SELECT * FROM employee ";

    private ConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    public boolean createEmployee(Employee employee) {
        log.info("create {}", employee);
        boolean result = false;

        try(Connection con = getConnection()) {
            PreparedStatement statement = con.prepareStatement(INSERT_EMPLOYEE);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getMiddleName());
            statement.setString(4, employee.getShortName());
            statement.setString(5, employee.getEmail());
            statement.setString(6, employee.getPhoneNumber());
            statement.setDate(7, Date.valueOf(employee.getBirthday()));
            statement.setDate(8, Date.valueOf(employee.getDateOfStart()));
            statement.setInt(9, employee.getEmpLevel().ordinal());
            statement.setInt(10, employee.getEnglishLevel().ordinal());
            statement.setString(11, employee.getSkype());
            statement.setInt(12, employee.getTeam().getTeamId());

            ResultSet rs = statement.executeQuery();
            result = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteById(int id) {
        log.info("delete {}", id);

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(DELETE_EMPLOYEE)) {
            statement.setInt(1, id);
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateEmployee(Employee employee) {
        log.info("update...");

        try (Connection con = getConnection()){
             PreparedStatement statement = con.prepareStatement(UPDATE_EMPLOYEE);

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getMiddleName());
            statement.setString(4, employee.getShortName());
            statement.setString(5, employee.getEmail());
            statement.setString(6, employee.getPhoneNumber());
            statement.setDate(7, Date.valueOf(employee.getBirthday()));
            statement.setDate(8, Date.valueOf(employee.getDateOfStart()));
            statement.setInt(9, employee.getEmpLevel().ordinal());
            statement.setInt(10, employee.getEnglishLevel().ordinal());
            statement.setString(11, employee.getSkype());
            statement.setInt(12, employee.getTeam().getTeamId());
            statement.setInt(13, employee.getEmployeeId());

            ResultSet rs = statement.executeQuery();
            rs.next();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Employee getEmployeeById(int employeeId) {
        log.info("get employee with {}", employeeId);

        Employee employee = null;

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_EMPLOYEE_BY_ID);
            statement.setInt(1, employeeId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Team team = new Team();
                team.setTeamId(rs.getInt("team_id"));

                employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setMiddleName(rs.getString("middle_name"));
                employee.setShortName(rs.getString("short_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhoneNumber(rs.getString("phone_number"));
                employee.setBirthday(rs.getDate("birthday").toLocalDate());
                employee.setDateOfStart(rs.getDate("date_of_start").toLocalDate());
                employee.setEmpLevel(rs.getInt("dev_level"));
                employee.setEnglishLevel(rs.getInt("english_level"));
                employee.setSkype(rs.getString("skype"));
                employee.setTeam(team);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    public List<Employee> getAllEmployees() {
        log.info("getAll");

        List<Employee> employees = new ArrayList<>();

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_EMPLOYEE);
            ResultSet rs = statement.executeQuery();
            Employee employee = null;

            while (rs.next()) {
                Team team = new Team();
                team.setTeamId(rs.getInt("team_id"));

                employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setMiddleName(rs.getString("middle_name"));
                employee.setShortName(rs.getString("short_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhoneNumber(rs.getString("phone_number"));
                employee.setBirthday(rs.getDate("birthday").toLocalDate());
                employee.setDateOfStart(rs.getDate("date_of_start").toLocalDate());
                employee.setEmpLevel(rs.getInt("dev_level"));
                employee.setEnglishLevel(rs.getInt("english_level"));
                employee.setSkype(rs.getString("skype"));
                employee.setTeam(team);

                employees.add(employee);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }
}
