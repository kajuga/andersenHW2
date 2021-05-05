package com.kosshit.anderse.task2_3.service;

import com.kosshit.anderse.task2_3.jdbc.ProjectDAO;
import com.kosshit.anderse.task2_3.model.Project;

import java.util.List;

public class ProjectService {

    private final ProjectDAO projectDAO;

    public ProjectService(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    public boolean create(Project project) {
        return projectDAO.createProject(project);
    }

    public Project getById(int projectId) {
        return projectDAO.getProjectById(projectId);
    }

    public void update(Project project) {
        projectDAO.updateProject(project);
    }

    public void delete(int projectId) {
        projectDAO.getProjectById(projectId);
    }

    public List<Project> getAll() {
        return projectDAO.getAllProjects();
    }
}
