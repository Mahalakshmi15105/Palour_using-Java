package com.smartgonext.model;

public class Employee {
    private String id;
    private String name;
    private String role; // "Master Stylist", "Color Specialist", "Senior Beautician", "Spa Therapist"
    private String phone;
    private String status; // "Active", "In Service", "On Break", "Leave"
    private double rating;
    private int monthlyAppointments;
    private String totalSalesRevenue;

    public Employee() {}

    public Employee(String id, String name, String role, String phone, String status, 
                    double rating, int monthlyAppointments, String totalSalesRevenue) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.phone = phone;
        this.status = status;
        this.rating = rating;
        this.monthlyAppointments = monthlyAppointments;
        this.totalSalesRevenue = totalSalesRevenue;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public int getMonthlyAppointments() { return monthlyAppointments; }
    public void setMonthlyAppointments(int monthlyAppointments) { this.monthlyAppointments = monthlyAppointments; }
    public String getTotalSalesRevenue() { return totalSalesRevenue; }
    public void setTotalSalesRevenue(String totalSalesRevenue) { this.totalSalesRevenue = totalSalesRevenue; }
}

