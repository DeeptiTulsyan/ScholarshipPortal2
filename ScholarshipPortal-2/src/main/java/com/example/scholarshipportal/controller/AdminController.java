package com.example.scholarshipportal.controller;

import com.example.scholarshipportal.model.Application;
import com.example.scholarshipportal.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Show all applications
    @GetMapping("/applications")
    public String viewApplications(Model model) {
        List<Application> applications = adminService.getAllApplications();
        model.addAttribute("applications", applications);
        return "admin_applications";
    }

    // Approve application
    @PostMapping("/approve/{id}")
    public String approveApplication(@PathVariable int id) {
        adminService.updateApplicationStatus(id, "Approved");
        return "redirect:/admin/applications";
    }

    // Reject application
    @PostMapping("/reject/{id}")
    public String rejectApplication(@PathVariable int id) {
        adminService.updateApplicationStatus(id, "Rejected");
        return "redirect:/admin/applications";
    }
}
