package com.kosshit.anderse.task2_3.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionBuilder {

    Connection getConnection() throws SQLException;
    boolean releaseConnection(Connection connection);
}
