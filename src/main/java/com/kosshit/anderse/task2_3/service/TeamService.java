package com.kosshit.anderse.task2_3.service;

import com.kosshit.anderse.task2_3.jdbc.TeamDAO;
import com.kosshit.anderse.task2_3.model.Team;

import java.util.List;

public class TeamService {

    private final TeamDAO teamDAO;


    public TeamService(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public boolean create(Team team) {
        return teamDAO.createTeam(team);
    }

    public Team getById(int teamId) {
        return teamDAO.getTeamById(teamId);
    }

    public void update(Team team) {
        teamDAO.updateTeam(team);
    }

    public void delete(int teamId) {
        teamDAO.deleteById(teamId);
    }

    public List<Team> getAll() {
        return teamDAO.getAllTeams();
    }
}
