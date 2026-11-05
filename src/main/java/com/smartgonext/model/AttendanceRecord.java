package com.smartgonext.model;

public class AttendanceRecord {
    private String id;
    private String staffName;
    private String role;
    private String checkInTime;
    private String checkOutTime;
    private String status; // "Present", "Late", "On Leave", "Half Day"
    private double hoursWorked;

    public AttendanceRecord() {}

    public AttendanceRecord(String id, String staffName, String role, String checkInTime, 
                            String checkOutTime, String status, double hoursWorked) {
        this.id = id;
        this.staffName = staffName;
        this.role = role;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.status = status;
        this.hoursWorked = hoursWorked;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getStaffName() { return staffName; }
    public void setStaffName(String staffName) { this.staffName = staffName; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getCheckInTime() { return checkInTime; }
    public void setCheckInTime(String checkInTime) { this.checkInTime = checkInTime; }
    public String getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(String checkOutTime) { this.checkOutTime = checkOutTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }
}

