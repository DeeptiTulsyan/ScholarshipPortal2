package com.example.scholarshipportal.service;

import com.example.scholarshipportal.model.Application;
import com.example.scholarshipportal.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    public List<Application> getAllApplications() {
        logger.info("Fetching all applications for admin view...");
        List<Application> applications = adminRepository.findAllApplications();

        if (applications.isEmpty()) {
            logger.warn("No applications found in the database.");
        } else {
            logger.info("Fetched {} applications successfully.", applications.size());
        }

        return applications;
    }

    public boolean updateApplicationStatus(int id, String status) {
        logger.info("Updating application ID {} to status '{}'", id, status);
        boolean success = adminRepository.updateStatus(id, status);

        if (success) {
            logger.info("Application ID {} successfully updated to '{}'", id, status);
        } else {
            logger.error("Failed to update status for Application ID {}", id);
        }

        return success;
    }
}
