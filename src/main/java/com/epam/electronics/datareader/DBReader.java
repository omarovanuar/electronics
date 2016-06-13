package com.epam.electronics.datareader;

import com.epam.electronics.entity.electronic_name.Name;
import com.epam.electronics.entity.Electronic;
import com.epam.electronics.entity.ElectronicFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReader implements ReaderInterface {
    private static final String[] SQL_STATEMENT = {"SELECT * FROM fridge", "SELECT * FROM vacuum_cleaner", "SELECT * FROM washing_machine", "SELECT * FROM tv", "SELECT * FROM laptop"};


    @Override
    public List<Electronic> readData() throws IOException {
        List<Electronic> list = new ArrayList<>();
        Connection con = DBUtil.getConnection();
        ResultSet rs;

        try (Statement stmt = con.createStatement()){
            int sqlIndex = 0;
            while (sqlIndex < SQL_STATEMENT.length) {
                rs = stmt.executeQuery(SQL_STATEMENT[sqlIndex]);
                int parameterNumber = rs.getMetaData().getColumnCount() + 1; //number of columns from ResultSet, + 1 for Name of electronic
                String[] strings = new String[parameterNumber];
                while (rs.next()) {
                    String electronicName = Name.values()[sqlIndex].name();
                    strings[0] = electronicName;
                    for (int i = 1; i < parameterNumber; i++) {
                        strings[i] = rs.getString(i);
                    }
                    list.add(ElectronicFactory.createElectronic(strings));
                }
                sqlIndex++;
            }
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        } finally {
            DBUtil.close(con);
        }
        return list;
    }
}
