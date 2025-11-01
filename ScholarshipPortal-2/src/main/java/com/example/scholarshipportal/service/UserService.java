package com.example.scholarshipportal.service;

import com.example.scholarshipportal.model.User;
import com.example.scholarshipportal.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) { this.repo = repo; }

    public boolean register(User user) {
        if (user.getUsername() == null || user.getPassword() == null || user.getRole() == null) return false;
        if (repo.existsByUsername(user.getUsername())) return false;
        return repo.save(user);
    }

    public User login(String username, String password) {
        return repo.findByUsernameAndPassword(username, password);
    }
}
