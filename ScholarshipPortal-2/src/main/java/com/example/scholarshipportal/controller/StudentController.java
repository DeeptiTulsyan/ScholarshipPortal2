package com.example.scholarshipportal.controller;

import com.example.scholarshipportal.model.Scholarship;
import com.example.scholarshipportal.model.Application;
import com.example.scholarshipportal.service.ApplicationService;
import com.example.scholarshipportal.service.ScholarshipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final ScholarshipService scholarshipService;

    public StudentController(ScholarshipService scholarshipService) {
        this.scholarshipService = scholarshipService;
    }

    @Autowired
    private ApplicationService applicationService;

    // Home page for students
    @GetMapping("/student/home")
    public String studentHome(Model model) {
        logger.info("Student accessed the home page to view available scholarships.");

        List<Scholarship> scholarships = scholarshipService.getAllScholarships();

        if (scholarships.isEmpty()) {
            logger.warn("No scholarships found in the database.");
        } else {
            logger.info("Fetched {} scholarships from the database.", scholarships.size());
        }

        model.addAttribute("scholarships", scholarships);
        return "studentHome";
    }

    // Show scholarship application form
    @GetMapping("/student/apply/{id}")
    public String showApplicationForm(@PathVariable int id, Model model) {
        logger.info("Student requested application form for scholarship ID: {}", id);

        Scholarship scholarship = scholarshipService.getScholarshipById(id);

        if (scholarship == null) {
            logger.error("Scholarship with ID {} not found.", id);
            model.addAttribute("error", "Scholarship not found.");
            return "errorPage";
        }

        model.addAttribute("scholarship", scholarship);
        logger.info("Displaying application form for scholarship '{}'.", scholarship.getName());
        return "applyScholarship";
    }

    // Submit scholarship application
    @PostMapping("/student/apply")
    public String submitApplication(@ModelAttribute Application app, Model model) {
        logger.info("Student '{}' is submitting an application for scholarship '{}'.",
                app.getStudentEmail(), app.getScholarshipName());

        try {
            app.setStatus("Pending");
            applicationService.saveApplication(app);
            logger.info("Application for '{}' submitted successfully by '{}'.",
                    app.getScholarshipName(), app.getStudentEmail());
            model.addAttribute("message", "Application submitted successfully!");
        } catch (Exception e) {
            logger.error("Error submitting application for '{}': {}", app.getScholarshipName(), e.getMessage());
            model.addAttribute("error", "Something went wrong while submitting your application.");
            return "errorPage";
        }

        return "applicationSuccess";
    }

    // View application status by email
    @GetMapping("/student/status")
    public String viewApplicationStatus(@RequestParam String email, Model model) {
        String trimmedEmail = email.trim().toLowerCase();
        logger.info("Student '{}' requested to view their application status.", trimmedEmail);

        List<Application> applications = applicationService.getApplicationsByEmail(trimmedEmail);

        if (applications.isEmpty()) {
            logger.warn("No applications found for email '{}'.", trimmedEmail);
        } else {
            logger.info("Found {} applications for student '{}'.", applications.size(), trimmedEmail);
        }

        model.addAttribute("applications", applications);
        return "applicationStatus";
    }
}
