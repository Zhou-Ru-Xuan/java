package com.zhouruxuan.structural.bridge.demo1;

import java.sql.*;

public class JDBCdemo {
    static final String DB_URL = "jdbc:mysql://localhost:3306";
    static final String USER = "root";
    static final String PASS = "yami1234567";
    static final String QUERY = "SELECT * from test.api_user";

    public static void main(String[] args) throws ClassNotFoundException {
        // Open a connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);) {
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("nickname: " + rs.getString("nickname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}