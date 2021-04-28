package com.kosshit.anderse.task2_3.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@EqualsAndHashCode(exclude = "feedId")
public class Feedback {
    private int feedId;
    private String description;
    private LocalDate date;
}
