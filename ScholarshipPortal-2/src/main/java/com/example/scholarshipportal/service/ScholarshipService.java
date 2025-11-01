package com.example.scholarshipportal.service;

import com.example.scholarshipportal.model.Scholarship;
import com.example.scholarshipportal.repository.ScholarshipRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScholarshipService {

    private static final Logger logger = LoggerFactory.getLogger(ScholarshipService.class);

    private final ScholarshipRepository scholarshipRepository;

    public ScholarshipService(ScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    public List<Scholarship> getAllScholarships() {
        logger.info("Fetching all scholarships from database");
        List<Scholarship> scholarships = scholarshipRepository.findAll();
        logger.debug("Number of scholarships fetched: {}", scholarships.size());
        return scholarships;
    }

    public Scholarship getScholarshipById(int id) {
        logger.info("Fetching scholarship with ID: {}", id);
        Scholarship scholarship = scholarshipRepository.findById(id);
        if (scholarship != null) {
            logger.debug("Scholarship found: {}", scholarship.getName());
        } else {
            logger.warn("No scholarship found with ID: {}", id);
        }
        return scholarship;
    }
}
