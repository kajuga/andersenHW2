package com.kosshit.anderse.task2_3.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode(exclude = "projectId")
public class Project {
    private Integer projectId;
    private String nameOfProject;
    private String customer;
    private String duration;
    private String methodology;
    private Employee projectManager;
    private Team team;

    public Project() {
    }

    public Project(Integer projectId, String nameOfProject, String customer, String duration, String methodology, Employee projectManager, Team team) {
        this.projectId = projectId;
        this.nameOfProject = nameOfProject;
        this.customer = customer;
        this.duration = duration;
        this.methodology = methodology;
        this.projectManager = projectManager;
        this.team = team;
    }

    public boolean isNew() {
        return projectId == null;
    }
}