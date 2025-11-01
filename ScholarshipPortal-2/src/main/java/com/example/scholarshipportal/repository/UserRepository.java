package com.example.scholarshipportal.repository;

import com.example.scholarshipportal.model.User;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class UserRepository {

    private final DataSource dataSource;
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean save(User user) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword()); // NOTE: plain password for demo
            ps.setString(3, user.getRole());
            int affected = ps.executeUpdate();

            if (affected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) user.setId(rs.getInt(1));
                }
                logger.info("New user registered: {}", user.getUsername());
                return true;
            } else {
                logger.warn("User registration failed for username: {}", user.getUsername());
            }

        } catch (SQLException e) {
            logger.error("Error while saving user: {}", user.getUsername(), e);
        }
        return false;
    }

    public User findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT id, username, role FROM users WHERE username = ? AND password = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setRole(rs.getString("role"));
                    logger.info("User login successful: {}", username);
                    return u;
                } else {
                    logger.warn("Login failed: Invalid credentials for username: {}", username);
                }
            }
        } catch (SQLException e) {
            logger.error("Database error while logging in user: {}", username, e);
        }
        return null;
    }

    public boolean existsByUsername(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                boolean exists = rs.next();
                if (exists) {
                    logger.info("Username already exists: {}", username);
                } else {
                    logger.info("Username available: {}", username);
                }
                return exists;
            }

        } catch (SQLException e) {
            logger.error("Error checking if username exists: {}", username, e);
        }
        return false;
    }
}
