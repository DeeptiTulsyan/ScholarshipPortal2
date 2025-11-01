package com.example.scholarshipportal.controller;

import com.example.scholarshipportal.model.Scholarship;
import com.example.scholarshipportal.service.ApplicationService;
import com.example.scholarshipportal.service.ScholarshipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import com.example.scholarshipportal.model.Application;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    private final ScholarshipService scholarshipService;

    public StudentController(ScholarshipService scholarshipService) {
        this.scholarshipService = scholarshipService;
    }

    @GetMapping("/student/home")
    public String studentHome(Model model) {
        List<Scholarship> scholarships = scholarshipService.getAllScholarships();
        model.addAttribute("scholarships", scholarships);
        return "studentHome";
    }
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/student/apply/{id}")
    public String showApplicationForm(@PathVariable int id, Model model) {
        Scholarship scholarship = scholarshipService.getScholarshipById(id);
        model.addAttribute("scholarship", scholarship);
        return "applyScholarship";
    }

    @PostMapping("/student/apply")
    public String submitApplication(@ModelAttribute Application app, Model model) {
        app.setStatus("Pending");
        applicationService.saveApplication(app);
        model.addAttribute("message", "Application submitted successfully!");
        return "applicationSuccess";
    }
    //@GetMapping("/student/status")
    //public String viewApplicationStatus(@RequestParam String email, Model model) {
        //List<Application> applications = applicationService.getApplicationsByEmail(email);
        //model.addAttribute("applications", applications);
        //return "applicationStatus";
   // }
    @GetMapping("/student/status")
    public String viewApplicationStatus(@RequestParam String email, Model model) {
        String trimmedEmail = email.trim().toLowerCase();
        List<Application> applications = applicationService.getApplicationsByEmail(trimmedEmail);
        model.addAttribute("applications", applications);
        return "applicationStatus";
    }


}
