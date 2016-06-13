package com.epam.electronics.datareader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

class DBUtil {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private static final String DRIVER;
    private static final String URL;
    private static final String USER;
    private static final String PASS;

    static {
        DRIVER = resourceBundle.getString("driver");
        URL = resourceBundle.getString("URL");
        USER = resourceBundle.getString("user");
        PASS = resourceBundle.getString("password");
    }

    static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            System.out.println("OK");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found");
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connect");
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        }
        return con;
    }

    static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Connection can't be closed: " + e.getMessage());
            }
        }
    }
}
