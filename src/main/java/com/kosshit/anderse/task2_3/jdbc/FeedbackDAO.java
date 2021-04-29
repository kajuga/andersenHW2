package com.kosshit.anderse.task2_3.jdbc;

import com.kosshit.anderse.task2_3.connection.ConnectionBuilder;
import com.kosshit.anderse.task2_3.model.Employee;
import com.kosshit.anderse.task2_3.model.Feedback;
import com.kosshit.anderse.task2_3.model.Project;
import com.kosshit.anderse.task2_3.model.Team;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    private static final String CREATE_FEEDBACK = "INSERT INTO feed_back (description, date_of_fb) VALUES (?, ?)";

    private static final String DELETE_FEEDBACK = "DELETE FROM feed_back WHERE feed_back_id = ?";

    private static final String GET_ALL_FEEDBACK = "SELECT * FROM feed_back";

    private static final String GET_FEEDBACK_BY_ID = "SELECT * FROM feed_back AS fb WHERE fb.feed_back_id = ?";

    private static final String UPDATE_FEEDBACK = "UPDATE feed_back AS fb SET description = ?, date_of_fb = ? WHERE fb.feed_back_id = ? ";

    @Setter
    private ConnectionBuilder connectionBuilder;


    public boolean createFeedback (Feedback feedback) {
        boolean result = false;

        try(Connection con = getConnection()) {

            PreparedStatement statement = con.prepareStatement(CREATE_FEEDBACK);
            statement.setString(1, feedback.getDescription());
            statement.setDate(2, Date.valueOf(feedback.getDate()));

            ResultSet rs = statement.executeQuery();
            result = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public int deleteById(int feedbackId) {
        int result = 0;

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_FEEDBACK);

            statement.setInt(1, feedbackId);

            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Feedback> getAllFeedback() {

        List<Feedback> feedbacks = new ArrayList<>();

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_FEEDBACK);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Feedback feedback = new Feedback();
                feedback.setFeedId(rs.getInt("feed_back_id"));
                feedback.setDescription(rs.getString("description"));
                feedback.setDate(rs.getDate("date_of_fb").toLocalDate());

                feedbacks.add(feedback);

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return feedbacks;
    }

    public Feedback getFeedbacktById(int feedbackId) {
        Feedback feedback = null;

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_FEEDBACK_BY_ID);
            statement.setInt(1, feedbackId);
            ResultSet rs = statement.executeQuery();

            feedback.setFeedId(rs.getInt("feed_back_id"));
            feedback.setDescription(rs.getString("description"));
            feedback.setDate(rs.getDate("date_of_fb").toLocalDate());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedback;
    }

    public int updateFeedback(Feedback feedback) {
        int result = 0;

        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_FEEDBACK);

            statement.setString(1, feedback.getDescription());
            statement.setDate(2, Date.valueOf(feedback.getDate()));

            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }
}
