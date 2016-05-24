package br.com.nmsalone.parser.database;

import br.com.nmsalone.parser.constants.DatabaseConnectionParameters;
import br.com.nmsalone.parser.iomanager.FileManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connection factory
 */
public class ConnectionFactory {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static ConnectionFactory factory;

    /**
     * Get a factory
     *
     * @return
     */
    public static ConnectionFactory getInstance() {
        if(factory == null)
            factory = new ConnectionFactory();

        return factory;
    }



    private Properties properties;

    private FileManager fileManager;

    /**
     * Default constructor just used to load properties values
     */
    private ConnectionFactory() {
        fileManager = new FileManager();
        this.properties = fileManager.loadDatabaseProperties();
    }

    /**
     * Create new connection and set the object in an object wrapper
     * @return connection wrapper
     */
    public ConnectionWrapper newConnection() {
        final String login = properties.getProperty(DatabaseConnectionParameters.DB_USER);
        final String password = properties.getProperty(DatabaseConnectionParameters.DB_PASSWORD);
        ConnectionWrapper wrapper = null;

        Connection connection = null;

        try {
            final String strConnection = getStringConnectionWithValues();
            connection = DriverManager.getConnection(strConnection, login, password);
            wrapper = new ConnectionWrapper(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wrapper;
    }

    private String getStringConnectionWithValues() {
        final String stringConnection = "jdbc:postgresql://$hostname:$port/$dbname";
        return stringConnection.
                replace("$hostname", properties.getProperty(DatabaseConnectionParameters.DB_HOST)).
                replace("$port", properties.getProperty(DatabaseConnectionParameters.DB_PORT)).
                replace("$dbname", properties.getProperty(DatabaseConnectionParameters.DB_NAME));
    }

}
