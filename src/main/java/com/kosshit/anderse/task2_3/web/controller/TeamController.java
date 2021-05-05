package com.kosshit.anderse.task2_3.web.controller;

import com.kosshit.anderse.task2_3.model.Team;
import com.kosshit.anderse.task2_3.service.TeamService;

import java.util.List;

public class TeamController {

    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    public boolean create(Team team) {
        return service.create(team);
    }

    public Team getById(int teamId) {
        return service.getById(teamId);
    }

    public List<Team> getAll() {
        return service.getAll();
    }

    public void update(Team team) {
        service.update(team);
    }

    public void delete(int teamId) {
        service.delete(teamId);
    }
}
