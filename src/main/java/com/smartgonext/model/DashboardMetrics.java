package com.smartgonext.model;

public class DashboardMetrics {
    private String todaySales;
    private String salesGrowth;
    private int activeCustomers;
    private String customerGrowth;
    private int todayAppointments;
    private String appointmentNote;
    private int onDutyEmployees;
    private int totalEmployees;

    public DashboardMetrics() {}

    public DashboardMetrics(String todaySales, String salesGrowth, int activeCustomers, 
                            String customerGrowth, int todayAppointments, String appointmentNote, 
                            int onDutyEmployees, int totalEmployees) {
        this.todaySales = todaySales;
        this.salesGrowth = salesGrowth;
        this.activeCustomers = activeCustomers;
        this.customerGrowth = customerGrowth;
        this.todayAppointments = todayAppointments;
        this.appointmentNote = appointmentNote;
        this.onDutyEmployees = onDutyEmployees;
        this.totalEmployees = totalEmployees;
    }

    public String getTodaySales() { return todaySales; }
    public void setTodaySales(String todaySales) { this.todaySales = todaySales; }

    public String getSalesGrowth() { return salesGrowth; }
    public void setSalesGrowth(String salesGrowth) { this.salesGrowth = salesGrowth; }

    public int getActiveCustomers() { return activeCustomers; }
    public void setActiveCustomers(int activeCustomers) { this.activeCustomers = activeCustomers; }

    public String getCustomerGrowth() { return customerGrowth; }
    public void setCustomerGrowth(String customerGrowth) { this.customerGrowth = customerGrowth; }

    public int getTodayAppointments() { return todayAppointments; }
    public void setTodayAppointments(int todayAppointments) { this.todayAppointments = todayAppointments; }

    public String getAppointmentNote() { return appointmentNote; }
    public void setAppointmentNote(String appointmentNote) { this.appointmentNote = appointmentNote; }

    public int getOnDutyEmployees() { return onDutyEmployees; }
    public void setOnDutyEmployees(int onDutyEmployees) { this.onDutyEmployees = onDutyEmployees; }

    public int getTotalEmployees() { return totalEmployees; }
    public void setTotalEmployees(int totalEmployees) { this.totalEmployees = totalEmployees; }
}

