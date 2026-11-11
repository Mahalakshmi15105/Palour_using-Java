package com.smartgonext.tenant.dto;

public class CustomerStatsDto {

    private long totalCustomers;
    private long newCustomersThisMonth;
    private long activeCustomers;
    private long returningCustomers;

    public CustomerStatsDto() {}

    public CustomerStatsDto(long totalCustomers, long newCustomersThisMonth, long activeCustomers, long returningCustomers) {
        this.totalCustomers = totalCustomers;
        this.newCustomersThisMonth = newCustomersThisMonth;
        this.activeCustomers = activeCustomers;
        this.returningCustomers = returningCustomers;
    }

    public long getTotalCustomers() { return totalCustomers; }
    public void setTotalCustomers(long totalCustomers) { this.totalCustomers = totalCustomers; }

    public long getNewCustomersThisMonth() { return newCustomersThisMonth; }
    public void setNewCustomersThisMonth(long newCustomersThisMonth) { this.newCustomersThisMonth = newCustomersThisMonth; }

    public long getActiveCustomers() { return activeCustomers; }
    public void setActiveCustomers(long activeCustomers) { this.activeCustomers = activeCustomers; }

    public long getReturningCustomers() { return returningCustomers; }
    public void setReturningCustomers(long returningCustomers) { this.returningCustomers = returningCustomers; }
}

