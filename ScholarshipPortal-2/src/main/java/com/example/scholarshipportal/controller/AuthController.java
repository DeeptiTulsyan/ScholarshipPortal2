package com.example.scholarshipportal.controller;

import com.example.scholarshipportal.model.User;
import com.example.scholarshipportal.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;

    public AuthController(UserService userService) { 
        this.userService = userService; 
    }

    @GetMapping("/")
    public String index() {
        logger.info("Landing page accessed.");
        return "index";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        logger.info("Register page opened.");
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute User user, Model model) {
        logger.info("Registration attempt for username: {}", user.getUsername());
        boolean ok = userService.register(user);

        if (ok) {
            logger.info("Registration successful for username: {}", user.getUsername());
            model.addAttribute("message", "Registration successful! Redirecting to landing...");
            return "register_success";
        } else {
            logger.warn("Registration failed for username: {}", user.getUsername());
            model.addAttribute("error", "Registration failed — username may exist or input invalid.");
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        logger.info("Login page opened.");
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String role,
                              Model model) {
        logger.info("Login attempt for username: {} with role: {}", username, role);
        User u = userService.login(username, password);

        if (u != null && u.getRole() != null && u.getRole().equalsIgnoreCase(role)) {
            logger.info("Login successful for username: {} (role: {})", username, role);
            if ("admin".equalsIgnoreCase(u.getRole())) 
                return "redirect:/admin/home";
            else 
                return "redirect:/student/home";
        } else {
            logger.warn("Login failed for username: {} due to invalid credentials or role mismatch.", username);
            model.addAttribute("error", "Invalid credentials or role mismatch.");
            return "login";
        }
    }

    @GetMapping("/admin/home")
    public String adminHome() {
        logger.info("Admin home accessed.");
        return "adminHome";
    }
}
