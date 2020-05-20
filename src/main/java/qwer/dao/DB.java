package qwer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/dbrest";
    static final String USER = "postgres";
    static final String PASS = "1234";
    private static DB dbIsntance;

    public static Connection getInstance() {

        return getDBConnection();
    }
    public static Connection getDBConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.
                    getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

