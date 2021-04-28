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
    private int projectId;
    private String nameOfProject;
    private String customer;
    private String duration;
    private String methodology;
    private Employee projectManager;
    private Team team;

}