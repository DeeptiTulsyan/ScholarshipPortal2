package com.example.scholarshipportal.repository;

import com.example.scholarshipportal.model.Scholarship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScholarshipRepository {

    @Autowired
    private DataSource dataSource;

    public List<Scholarship> findAll() {
        List<Scholarship> scholarships = new ArrayList<>();
        String sql = "SELECT * FROM scholarship";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Scholarship s = new Scholarship();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDescription(rs.getString("description"));
                scholarships.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scholarships;
    }

    public Scholarship findById(int id) {
        Scholarship scholarship = null;
        String sql = "SELECT * FROM scholarship WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                scholarship = new Scholarship();
                scholarship.setId(rs.getInt("id"));
                scholarship.setName(rs.getString("name"));
                scholarship.setDescription(rs.getString("description"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scholarship;
    }
}
