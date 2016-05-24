package br.com.nmsalone.parser.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionWrapper {

    private final Connection connection;
    private Statement statement;

    public ConnectionWrapper(final Connection connection) {
        this.connection = connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement prepareStatement(final String query) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statement;
    }

    private void closeStatement() {
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
