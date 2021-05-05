package com.kosshit.anderse.task2_3.web.controller;

import com.kosshit.anderse.task2_3.model.Feedback;
import com.kosshit.anderse.task2_3.service.FeedbackService;

import java.util.List;

public class FeedbackController {

    private final FeedbackService service;

    public FeedbackController(FeedbackService service) {
        this.service = service;
    }

    public boolean create(Feedback feedback) {
        return service.create(feedback);
    }

    public Feedback getById(int feedbackId) {
        return service.getById(feedbackId);
    }

    public List<Feedback> getAll() {
        return service.getAll();
    }

    public void update(Feedback feedback) {
        service.update(feedback);
    }

    public void delete(int feedbackId) {
        service.delete(feedbackId);
    }
}
