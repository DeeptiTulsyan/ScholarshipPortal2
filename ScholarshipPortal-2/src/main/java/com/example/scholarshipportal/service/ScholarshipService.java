package com.example.scholarshipportal.service;

import com.example.scholarshipportal.model.Scholarship;
import com.example.scholarshipportal.repository.ScholarshipRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScholarshipService {

    private final ScholarshipRepository scholarshipRepository;

    public ScholarshipService(ScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    public List<Scholarship> getAllScholarships() {
        return scholarshipRepository.findAll();
    }

    public Scholarship getScholarshipById(int id) {
        return scholarshipRepository.findById(id);
    }
}
