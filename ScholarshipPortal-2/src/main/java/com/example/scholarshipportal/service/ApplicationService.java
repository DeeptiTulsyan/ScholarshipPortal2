package com.example.scholarshipportal.service;

import com.example.scholarshipportal.model.Application;
import com.example.scholarshipportal.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    private static final Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    public void saveApplication(Application app) {
        logger.info("Saving new application for student: {} ({}) for scholarship: {}",
                app.getStudentName(), app.getStudentEmail(), app.getScholarshipName());
        try {
            applicationRepository.save(app);
            logger.info("Application saved successfully for student email: {}", app.getStudentEmail());
        } catch (Exception e) {
            logger.error("Error saving application for student email: {}", app.getStudentEmail(), e);
        }
    }

    public List<Application> getApplicationsByEmail(String email) {
        logger.info("Fetching all applications for student email: {}", email);
        List<Application> applications = applicationRepository.findByStudentEmail(email);

        if (applications.isEmpty()) {
            logger.warn("No applications found for student email: {}", email);
        } else {
            logger.info("Found {} applications for student email: {}", applications.size(), email);
        }

        return applications;
    }
}
