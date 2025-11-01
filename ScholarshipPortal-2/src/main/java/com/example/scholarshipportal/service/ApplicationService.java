package com.example.scholarshipportal.service;

import com.example.scholarshipportal.model.Application;
import com.example.scholarshipportal.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public void saveApplication(Application app) {
        applicationRepository.save(app);
    }

    public List<Application> getApplicationsByEmail(String email) {
        return applicationRepository.findByStudentEmail(email);
    }
}
