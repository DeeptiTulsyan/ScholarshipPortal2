package com.example.scholarshipportal.service;

import com.example.scholarshipportal.model.Application;
import com.example.scholarshipportal.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Application> getAllApplications() {
        return adminRepository.findAllApplications();
    }

    public boolean updateApplicationStatus(int id, String status) {
        return adminRepository.updateStatus(id, status);
    }
}

