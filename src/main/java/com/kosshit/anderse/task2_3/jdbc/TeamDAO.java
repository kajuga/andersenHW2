package com.kosshit.anderse.task2_3.jdbc;

import com.kosshit.anderse.task2_3.connection.ConnectionBuilder;
import com.kosshit.anderse.task2_3.model.Team;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    private static final String CREATE_TEAM = "INSERT INTO team (team_name) VALUES (?) RETURNING team_id";

    private static final String DELETE_TEAM = "DELETE FROM team WHERE team_id = ? RETURNING team_id";

    private static final String GET_ALL_TEAM = "SELECT * FROM team";

    private static final String GET_TEAM_BY_ID = "SELECT * FROM team AS t WHERE t.team_id = ? ";

    private static final String UPDATE_TEAM = "UPDATE team AS t SET team_name = ? WHERE t.team_id = ? RETURNING team_id";

    @Setter
    private ConnectionBuilder connectionBuilder;

    public boolean createTeam (Team team) {
        boolean result = false;

        try(Connection con = getConnection()) {
            PreparedStatement statement = con.prepareStatement(CREATE_TEAM);

            statement.setString(1, team.getTeamName());

            ResultSet rs = statement.executeQuery();
            result = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void deleteById(int teamId) {

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_TEAM);
            statement.setInt(1, teamId);
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Team> getAllTeams() {

        List<Team> teams = new ArrayList<>();

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_TEAM);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Team team = new Team();
                team.setTeamId(rs.getInt("team_id"));
                team.setTeamName(rs.getString("team_name"));

                teams.add(team);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return teams;
    }

    public Team getTeamById(int teamId) {
        Team team = null;

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_TEAM_BY_ID);
            statement.setInt(1, teamId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                team = new Team();
                team.setTeamId(rs.getInt("team_id"));
                team.setTeamName(rs.getString("team_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return team;
    }

    public void updateTeam(Team team) {

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_TEAM);
            statement.setString(1, team.getTeamName());
            statement.setInt(2, team.getTeamId());

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
