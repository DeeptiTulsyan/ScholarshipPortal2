package com.example.scholarshipportal.repository;

import com.example.scholarshipportal.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepository {

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = LoggerFactory.getLogger(AdminRepository.class);

    // Fetch all applications
    public List<Application> findAllApplications() {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM applications ORDER BY id DESC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Application app = new Application();
                app.setId(rs.getInt("id"));
                app.setStudentName(rs.getString("student_name"));
                app.setStudentEmail(rs.getString("student_email"));
                app.setScholarshipId(rs.getInt("scholarship_id"));
                app.setScholarshipName(rs.getString("scholarship_name"));
                app.setStatus(rs.getString("status"));
                list.add(app);
            }

            logger.info("Fetched {} applications from database.", list.size());

        } catch (SQLException e) {
            logger.error("Error while fetching all applications", e);
        }
        return list;
    }

    // Update status (Approve/Reject)
    public boolean updateStatus(int id, String status) {
        String sql = "UPDATE applications SET status = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            boolean success = ps.executeUpdate() > 0;
            if (success) {
                logger.info("Updated status of application ID {} to {}", id, status);
            } else {
                logger.warn("No application found with ID {}", id);
            }
            return success;
        } catch (SQLException e) {
            logger.error("Error while updating application status for ID: {}", id, e);
            return false;
        }
    }
}
