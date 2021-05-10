package com.kosshit.anderse.task2_3.jdbc;

import com.kosshit.anderse.task2_3.connection.ConnectionBuilder;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.Project;
import com.kosshit.anderse.task2_3.model.Team;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProjectDAO {

    private static final String CREATE_PROJECT = "INSERT INTO project (name_of_project, customer, duration, methodology) VALUES (?, ?, ?, ?) RETURNING project_id";
    private static final String DELETE_PROJECT = "DELETE FROM project WHERE project_id = ? RETURNING project_id";

    private static final String GET_ALL_PROJECT = "SELECT * FROM project ";

    private static final String GET_PROJECT_BY_ID = "SELECT * FROM project AS p WHERE p.project_id = ? ";

    private static final String UPDATE_PROJECT = "UPDATE project AS p SET name_of_project = ?, customer = ?, duration = ?, methodology = ?, project_manager_id = ?, team_id = ? WHERE p.project_id = ? RETURNING project_id";

    @Setter
    private ConnectionBuilder connectionBuilder;


    public boolean createProject (Project project) {
        log.info("create {}", project);
        boolean result = false;

        try(Connection con = getConnection()) {

            PreparedStatement statement = con.prepareStatement(CREATE_PROJECT);
            statement.setString(1, project.getNameOfProject());
            statement.setString(2, project.getCustomer());
            statement.setString(3, project.getDuration());
            statement.setString(4, project.getMethodology());

            ResultSet rs = statement.executeQuery();
            result = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void deleteById(int projectId) {
        log.info("delete {}", projectId);
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_PROJECT)){
            statement.setInt(1, projectId);
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Project> getAllProjects() {
        log.info("getAll");
        List<Project> projects = new ArrayList<>();

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_PROJECT);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Team team = new Team();
                team.setTeamId(rs.getInt("team_id"));

                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("project_manager_id"));

                Project project = new Project();
                project.setProjectId(rs.getInt("project_id"));
                project.setNameOfProject(rs.getString("name_of_project"));
                project.setCustomer(rs.getString("customer"));
                project.setDuration(rs.getString("duration"));
                project.setMethodology(rs.getString("methodology"));
                project.setTeam(team);
                project.setProjectManager(employee);

                projects.add(project);

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

    public Project getProjectById(int projectId) {
        log.info("get {}", projectId);

        Project project = null;

        try(Connection connection = getConnection()) {
           PreparedStatement statement = connection.prepareStatement(GET_PROJECT_BY_ID);
           statement.setInt(1, projectId);
           ResultSet rs = statement.executeQuery();

           while(rs.next()) {
               Team team = new Team();
               team.setTeamId(rs.getInt("team_id"));

               Employee employee = new Employee();
               employee.setEmployeeId(rs.getInt("project_manager_id"));

               project = new Project();
               project.setProjectId(rs.getInt("project_id"));
               project.setNameOfProject(rs.getString("name_of_project"));
               project.setCustomer(rs.getString("customer"));
               project.setDuration(rs.getString("duration"));
               project.setMethodology(rs.getString("methodology"));
               project.setTeam(team);
               project.setProjectManager(employee);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return project;
    }

    public void updateProject(Project project) {
        log.info("update {}", project);
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_PROJECT);

            statement.setString(1, project.getNameOfProject());
            statement.setString(2, project.getCustomer());
            statement.setString(3, project.getDuration());
            statement.setString(4, project.getMethodology());
            statement.setInt(5, project.getProjectManager().getEmployeeId());
            statement.setInt(6, project.getTeam().getTeamId());
            statement.setInt(7, project.getProjectId());

            ResultSet rs = statement.executeQuery();
            rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }

}
