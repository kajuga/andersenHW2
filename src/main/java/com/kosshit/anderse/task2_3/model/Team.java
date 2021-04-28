package com.kosshit.anderse.task2_3.model;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(exclude = "teamId")
public class Team {
    int teamId;
    String teamName;
    List<Employee> employees;

}