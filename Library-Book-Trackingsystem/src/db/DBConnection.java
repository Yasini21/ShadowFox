package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // WHY: store DB details in one place
    private static final String URL =
        "jdbc:mysql://localhost:3307/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root@1243!";

    // WHY: one method to give connection to entire project
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
            return null;
        }
    }
}
