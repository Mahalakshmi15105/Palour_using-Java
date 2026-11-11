package com.smartgonext.tenant.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomerListItemDto {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String gender;
    private int totalVisits;
    private LocalDate lastVisitDate;
    private BigDecimal totalSpending;
    private String membershipStatus;
    private String membershipPlanName;
    private int loyaltyPoints;
    private String status;

    public CustomerListItemDto() {}

    public CustomerListItemDto(Long id, String name, String phone, String email, String gender,
                               int totalVisits, LocalDate lastVisitDate, BigDecimal totalSpending,
                               String membershipStatus, String membershipPlanName, int loyaltyPoints, String status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.totalVisits = totalVisits;
        this.lastVisitDate = lastVisitDate;
        this.totalSpending = totalSpending != null ? totalSpending : BigDecimal.ZERO;
        this.membershipStatus = membershipStatus != null ? membershipStatus : "Standard";
        this.membershipPlanName = membershipPlanName;
        this.loyaltyPoints = loyaltyPoints;
        this.status = status != null ? status : "Active";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public int getTotalVisits() { return totalVisits; }
    public void setTotalVisits(int totalVisits) { this.totalVisits = totalVisits; }

    public LocalDate getLastVisitDate() { return lastVisitDate; }
    public void setLastVisitDate(LocalDate lastVisitDate) { this.lastVisitDate = lastVisitDate; }

    public BigDecimal getTotalSpending() { return totalSpending; }
    public void setTotalSpending(BigDecimal totalSpending) { this.totalSpending = totalSpending; }

    public String getMembershipStatus() { return membershipStatus; }
    public void setMembershipStatus(String membershipStatus) { this.membershipStatus = membershipStatus; }

    public String getMembershipPlanName() { return membershipPlanName; }
    public void setMembershipPlanName(String membershipPlanName) { this.membershipPlanName = membershipPlanName; }

    public int getLoyaltyPoints() { return loyaltyPoints; }
    public void setLoyaltyPoints(int loyaltyPoints) { this.loyaltyPoints = loyaltyPoints; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

