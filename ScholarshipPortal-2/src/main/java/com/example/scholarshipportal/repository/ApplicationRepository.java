package com.example.scholarshipportal.repository;

import com.example.scholarshipportal.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ApplicationRepository {

    @Autowired
    private DataSource dataSource;

    public void save(Application app) {
        String sql = "INSERT INTO applications (student_name, student_email, scholarship_id, scholarship_name, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, app.getStudentName());
            stmt.setString(2, app.getStudentEmail());
            stmt.setInt(3, app.getScholarshipId());
            stmt.setString(4, app.getScholarshipName());
            stmt.setString(5, app.getStatus());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Application> findByStudentEmail(String email) {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE student_email = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

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
}
