package com.kosshit.anderse.task2_3.model;

import lombok.*;
import java.util.List;


@Setter
@Getter
@ToString
@EqualsAndHashCode(exclude = "teamId")
public class Team {
    Integer teamId;
    String teamName;
    List<Employee> employees;

    public Team() {
    }

    public Team(Integer teamId, String teamName, List<Employee> employees) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.employees = employees;
    }

    public boolean isNew() {
        return teamId == null;
    }
}