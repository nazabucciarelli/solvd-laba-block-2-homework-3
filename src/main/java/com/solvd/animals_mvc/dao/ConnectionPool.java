package com.solvd.animals_mvc.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    private static String url;
    private static String userName;
    private static String password;
    private static Properties properties;
    private static final int INITIAL_POOL_SIZE = 5;
    private static final List<Connection> connectionPool = new ArrayList<>();
    private static final List<Connection> usedConnections = new ArrayList<>();
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    static {
        properties = new Properties();
        try (FileInputStream f = new FileInputStream("src/main/resources/" +
                                                        "config.properties")) {
            properties.load(f);
        } catch (IOException e) {
            LOGGER.info(e);
        }
        url = properties.getProperty("db.url");
        userName = properties.getProperty("db.username");
        password = properties.getProperty("db.password");
        try {
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                Connection connection = createConnection();
                connectionPool.add(connection);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    public synchronized static Connection getConnection() throws SQLException {
        if (connectionPool.isEmpty()) {
            throw new SQLException();
        }
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public synchronized static boolean releaseConnection(Connection connection) {
        if (usedConnections.remove(connection)) {
            connectionPool.add(connection);
            return true;
        }
        return false;
    }

    public static int getPoolSize() {
        return connectionPool.size();
    }

    public static String getFramework() {
        return properties.getProperty("framework");
    }
}
