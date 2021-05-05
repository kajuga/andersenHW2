package com.kosshit.anderse.task2_3.service;

import com.kosshit.anderse.task2_3.jdbc.FeedbackDAO;
import com.kosshit.anderse.task2_3.model.Feedback;

import java.util.List;

public class FeedbackService {

    private final FeedbackDAO feedbackDAO;

    public FeedbackService(FeedbackDAO feedbackDAO) {
        this.feedbackDAO = feedbackDAO;
    }

    public boolean create(Feedback feedback) {
        return feedbackDAO.createFeedback(feedback);
    }

    public Feedback getById(int feedbackId) {
        return feedbackDAO.getFeedbackById(feedbackId);
    }

    public void update(Feedback feedback) {
        feedbackDAO.updateFeedback(feedback);
    }

    public void delete(int feedbackId) {
        feedbackDAO.deleteById(feedbackId);
    }

    public List<Feedback> getAll() {
        return feedbackDAO.getAllFeedback();
    }
}
