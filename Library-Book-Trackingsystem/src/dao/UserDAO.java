package dao;

import db.DBConnection;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // WHY: Add a new user to the database
    public void addUser(String name, String role) {

        String sql = "INSERT INTO users (name, role) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, role);
            ps.executeUpdate();

            System.out.println("User added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // WHY: Fetch a user by ID (for validation later)
    public User getUserById(int userId) {

        String sql = "SELECT * FROM users WHERE user_id = ?";
        User user = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("role")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // WHY: Fetch all users (useful for display & selection)
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("role")
                );
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
