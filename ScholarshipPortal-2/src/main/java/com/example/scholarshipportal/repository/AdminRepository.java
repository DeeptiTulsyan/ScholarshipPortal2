package com.example.scholarshipportal.repository;

import com.example.scholarshipportal.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepository {

    @Autowired
    private DataSource dataSource;

    // Fetch all applications with joined student and scholarship info
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
        } catch (SQLException e) {
            e.printStackTrace();
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
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

