package com.lyssn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    // DB connection info - should probably move these to a config file later
    private static final String DB_URL = "jdbc:postgresql://localhost:5433/mydb";
    private static final String DB_USER = "admin";
    private static final String DB_PASS = "admin";

    // Get a fresh connection each time to avoid timeout issues
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    // Grab all users from the DB - returns empty list if none found
    public static List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users ORDER BY userid";  // ordered for consistency

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                userList.add(createUserFromResultSet(rs));
            }
        }
        return userList;
    }

    // Find one user by their ID - returns null if not found
    public static User getUserById(long userid) throws SQLException {
        String query = "SELECT * FROM users WHERE userid = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, userid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return createUserFromResultSet(rs);
                }
            }
        }
        return null;  // user not found
    }

    // Update existing user info - returns true if something was actually updated
    public static boolean updateUser(long userid, String fname, String name) throws SQLException {
        String query = "UPDATE users SET fname = ?, name = ? WHERE userid = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, fname);
            stmt.setString(2, name);
            stmt.setLong(3, userid);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    // Helper method to avoid duplicate code when creating User objects
    private static User createUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("userid"),
                rs.getString("fname"),
                rs.getString("name"),
                rs.getTimestamp("signupdate")
        );
    }
}