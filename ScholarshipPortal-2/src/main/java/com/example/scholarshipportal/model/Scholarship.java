package com.example.scholarshipportal.model;

public class Scholarship {
    private int id;
    private String name;
    private String description;
    private double amount;
    private String eligibility;

    public Scholarship() {}

    public Scholarship(int id, String name, String description, double amount, String eligibility) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.eligibility = eligibility;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getEligibility() { return eligibility; }
    public void setEligibility(String eligibility) { this.eligibility = eligibility; }
}
