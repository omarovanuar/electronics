package com.epam.electronics.datareader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

class DBUtil {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/electronic_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "adminroot";

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
