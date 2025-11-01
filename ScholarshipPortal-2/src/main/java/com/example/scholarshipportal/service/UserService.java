package com.example.scholarshipportal.service;

import com.example.scholarshipportal.model.User;
import com.example.scholarshipportal.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public boolean register(User user) {
        logger.info("Attempting to register user: {}", user.getUsername());

        if (user.getUsername() == null || user.getPassword() == null || user.getRole() == null) {
            logger.warn("Registration failed: Missing required fields for user");
            return false;
        }

        if (repo.existsByUsername(user.getUsername())) {
            logger.warn("Registration failed: Username '{}' already exists", user.getUsername());
            return false;
        }

        boolean saved = repo.save(user);
        if (saved) {
            logger.info("User '{}' registered successfully", user.getUsername());
        } else {
            logger.error("Failed to save user '{}' in database", user.getUsername());
        }
        return saved;
    }

    public User login(String username, String password) {
        logger.info("Login attempt for username: {}", username);

        User user = repo.findByUsernameAndPassword(username, password);
        if (user != null) {
            logger.info("Login successful for user: {}", username);
        } else {
            logger.warn("Login failed: Invalid credentials for user: {}", username);
        }
        return user;
    }
}
