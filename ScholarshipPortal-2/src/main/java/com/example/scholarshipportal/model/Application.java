package com.example.scholarshipportal.model;

public class Application {
    private int id;
    private String studentName;
    private String studentEmail;
    private int scholarshipId;
    private String scholarshipName;
    private String status;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }

    public int getScholarshipId() { return scholarshipId; }
    public void setScholarshipId(int scholarshipId) { this.scholarshipId = scholarshipId; }

    public String getScholarshipName() { return scholarshipName; }
    public void setScholarshipName(String scholarshipName) { this.scholarshipName = scholarshipName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
