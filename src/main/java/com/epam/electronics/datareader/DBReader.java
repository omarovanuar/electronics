package com.epam.electronics.datareader;

import com.epam.electronics.electronic_name.Name;
import com.epam.electronics.entity.Electronic;
import com.epam.electronics.entity.ElectronicFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DBReader implements ReaderInterface {
    private static final String[] SQL_STATEMENT = {"SELECT * FROM fridge", "SELECT * FROM vacuum_cleaner", "SELECT * FROM washing_machine", "SELECT * FROM tv", "SELECT * FROM laptop"};

    @Override
    public List<Electronic> readData() throws IOException {
        List<Electronic> list = new ArrayList<>();
        Connection con = getConnection();
        ResultSetMetaData rsmd;
        try (Statement stmt = con.createStatement()){
            int sqlIndex = 0;
            while (sqlIndex < SQL_STATEMENT.length) {
                ResultSet rs = stmt.executeQuery(SQL_STATEMENT[sqlIndex]);
                rsmd = rs.getMetaData();
                String[] strings = new String[8];
                while (rs.next()) {
                    strings[0] = Name.values()[sqlIndex].name();
                    for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                        strings[i] = rs.getString(i);
                    }
                    list.add(ElectronicFactory.createFromFile(strings));
                }
                sqlIndex++;
            }
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        }
        return list;
    }

    private Connection getConnection() {
        ResourceBundle rb = ResourceBundle.getBundle("database");
        String driver = rb.getString("driver");
        String url = rb.getString("dbURL");
        String user = rb.getString("user");
        String password = rb.getString("password");
        try {
            Class.forName(driver);
            System.out.println("OK");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found");
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connect");
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        }
        return con;
    }
}
