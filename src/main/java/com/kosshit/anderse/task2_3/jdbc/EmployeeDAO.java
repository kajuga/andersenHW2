package com.kosshit.anderse.task2_3.jdbc;

import com.kosshit.anderse.task2_3.connection.ConnectionBuilder;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.Team;
import lombok.Setter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {

    private static final String INSERT_EMPLOYEE =
            "INSERT INTO employee (first_name, last_name, middle_name, short_name, " +
                    "email, phone_number, birthday, date_of_start, dev_level, english_level, skype, team_id)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE_EMPLOYEE =
            "DELETE FROM employee WHERE employee_id = ?";

    private static final String UPDATE_EMPLOYEE =
            "UPDATE employee SET " +
                    " first_name = ?, last_name = ?, middle_name = ?, short_name = ?, email = ?, phone_number = ?, birthday = ?, date_of_start = ?, dev_level = ?, english_level = ?, skype = ?, team_id = ? " +
                    " WHERE employee_id = ?";

    private static final String SELECT_ALL_WITH_TEAM = "SELECT * FROM employee AS e JOIN team AS t ON e.team_id = t.team_id WHERE e.team_id = ?";

    @Setter
    private ConnectionBuilder connectionBuilder;

    public boolean createEmployee(Employee employee) {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public int deleteById(int id) {

        int result = 0;

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(DELETE_EMPLOYEE)) {

            statement.setInt(1, id);

            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int updateEmployee(Employee employee) {
        int result = 0;

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

            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Employee> findAllEmployeesWithTeam(int teamId) {

        List<Employee> employeesLists = new ArrayList<>();


        try (Connection con = getConnection()) {
             PreparedStatement statement = con.prepareStatement(SELECT_ALL_WITH_TEAM);
             statement.setInt(1, teamId);
             ResultSet resultSet = statement.executeQuery();

            //команды
            while (resultSet.next()) {
                Team team = new Team();
                team.setTeamId(resultSet.getInt("team_id"));
                team.setTeamName(resultSet.getString("team_name"));

                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setMiddleName(resultSet.getString("middle_name"));
                employee.setShortName(resultSet.getString("short_name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPhoneNumber(resultSet.getString("phone_number"));
                employee.setBirthday(resultSet.getDate("birthday").toLocalDate());
                employee.setDateOfStart(resultSet.getDate("date_of_start").toLocalDate());
                employee.setEmpLevel(resultSet.getInt("dev_level"));
                employee.setEnglishLevel(resultSet.getInt("english_level"));
                employee.setSkype(resultSet.getString("skype"));
                employee.setTeam(team);

                employeesLists.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeesLists;
    }

    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }
}
