package com.kosshit.anderse.task2_3.web.controller;

import com.kosshit.anderse.task2_3.model.Project;
import com.kosshit.anderse.task2_3.service.ProjectService;

import java.util.List;

public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    public boolean create(Project project) {
        return service.create(project);
    }

    public Project getById(int projectId) {
        return service.getById(projectId);
    }

    public List<Project> getAll() {
        return service.getAll();
    }

    public void update(Project project) {
        service.update(project);
    }

    public void delete(int projectId) {
        service.delete(projectId);
    }
}
