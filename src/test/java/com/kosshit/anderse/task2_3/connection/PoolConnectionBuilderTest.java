package com.kosshit.anderse.task2_3.connection;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;


public class PoolConnectionBuilderTest {

    @Test
    public void create() {
        PoolConnectionBuilder connectionPool = null;
        try {
            connectionPool = PoolConnectionBuilder.create("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            assertTrue(connectionPool.getConnection().isValid(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}