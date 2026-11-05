package com.smartgonext.model;

public class Appointment {
    private String id;
    private String customerName;
    private String customerPhone;
    private String serviceName;
    private String stylistName;
    private String timeSlot;
    private String status; // "In-Chair", "Confirmed", "Waiting", "Completed"
    private double amount;

    public Appointment() {}

    public Appointment(String id, String customerName, String customerPhone, String serviceName, 
                       String stylistName, String timeSlot, String status, double amount) {
        this.id = id;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.serviceName = serviceName;
        this.stylistName = stylistName;
        this.timeSlot = timeSlot;
        this.status = status;
        this.amount = amount;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public String getStylistName() { return stylistName; }
    public void setStylistName(String stylistName) { this.stylistName = stylistName; }

    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}

