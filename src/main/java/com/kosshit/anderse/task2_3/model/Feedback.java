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
    private Integer feedId;
    private String description;
    private LocalDate date;

    public Feedback() {
    }

    public Feedback(Integer feedId, String description, LocalDate date) {
        this.feedId = feedId;
        this.description = description;
        this.date = date;
    }

    public boolean isNew() {
        return feedId == null;
    }
}
