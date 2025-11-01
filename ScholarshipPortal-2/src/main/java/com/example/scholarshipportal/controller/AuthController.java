package com.example.scholarshipportal.controller;

import com.example.scholarshipportal.model.User;
import com.example.scholarshipportal.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;
    public AuthController(UserService userService) { this.userService = userService; }

    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute User user, Model model) {
        boolean ok = userService.register(user);
        if (ok) {
            model.addAttribute("message", "Registration successful! Redirecting to landing...");
            return "register_success";
        } else {
            model.addAttribute("error", "Registration failed — username may exist or input invalid.");
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLogin(Model model) { return "login"; }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String role,
                              Model model) {
        User u = userService.login(username, password);
        if (u != null && u.getRole() != null && u.getRole().equalsIgnoreCase(role)) {
            if ("admin".equalsIgnoreCase(u.getRole())) return "redirect:/admin/home";
            else return "redirect:/student/home";
        } else {
            model.addAttribute("error", "Invalid credentials or role mismatch.");
            return "login";
        }
    }

    //@GetMapping("/student/home")
    //public String studentHome() { return "studentHome"; }

    @GetMapping("/admin/home")
    public String adminHome() { return "adminHome"; }
}
