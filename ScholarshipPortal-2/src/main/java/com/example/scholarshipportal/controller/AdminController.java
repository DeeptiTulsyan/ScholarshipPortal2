package com.example.scholarshipportal.controller;

import com.example.scholarshipportal.model.Application;
import com.example.scholarshipportal.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    // Show all applications
    @GetMapping("/applications")
    public String viewApplications(Model model) {
        logger.info("Admin requested to view all scholarship applications.");
        List<Application> applications = adminService.getAllApplications();

        if (applications.isEmpty()) {
            logger.warn("No applications found in the database.");
        } else {
            logger.info("Fetched {} applications from the database.", applications.size());
        }

        model.addAttribute("applications", applications);
        return "admin_applications";
    }

    // Approve application
    @PostMapping("/approve/{id}")
    public String approveApplication(@PathVariable int id) {
        logger.info("Admin attempting to approve application with ID: {}", id);
        try {
            adminService.updateApplicationStatus(id, "Approved");
            logger.info("Application ID {} approved successfully.", id);
        } catch (Exception e) {
            logger.error("Error while approving application ID {}: {}", id, e.getMessage());
        }
        return "redirect:/admin/applications";
    }

    // Reject application
    @PostMapping("/reject/{id}")
    public String rejectApplication(@PathVariable int id) {
        logger.info("Admin attempting to reject application with ID: {}", id);
        try {
            adminService.updateApplicationStatus(id, "Rejected");
            logger.info("Application ID {} rejected successfully.", id);
        } catch (Exception e) {
            logger.error("Error while rejecting application ID {}: {}", id, e.getMessage());
        }
        return "redirect:/admin/applications";
    }
}
