package com.smartgonext.tenant.dto;

import com.smartgonext.tenant.entity.AppointmentEntity;
import com.smartgonext.tenant.entity.CustomerEntity;
import com.smartgonext.tenant.entity.CustomerMembershipEntity;
import com.smartgonext.tenant.entity.InvoiceEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerDetailDto {

    private CustomerEntity customer;
    private int totalVisits;
    private LocalDate lastVisitDate;
    private BigDecimal totalSpending;
    private int loyaltyPoints;
    private boolean hasMembership;
    private String membershipTier;
    private LocalDate membershipStartDate;
    private LocalDate membershipExpiresAt;
    private String membershipStatus;

    private List<AppointmentEntity> appointments = new ArrayList<>();
    private List<InvoiceEntity> invoices = new ArrayList<>();
    private List<CustomerMembershipEntity> memberships = new ArrayList<>();

    public CustomerDetailDto() {}

    public CustomerEntity getCustomer() { return customer; }
    public void setCustomer(CustomerEntity customer) { this.customer = customer; }

    public int getTotalVisits() { return totalVisits; }
    public void setTotalVisits(int totalVisits) { this.totalVisits = totalVisits; }

    public LocalDate getLastVisitDate() { return lastVisitDate; }
    public void setLastVisitDate(LocalDate lastVisitDate) { this.lastVisitDate = lastVisitDate; }

    public BigDecimal getTotalSpending() { return totalSpending; }
    public void setTotalSpending(BigDecimal totalSpending) { this.totalSpending = totalSpending; }

    public int getLoyaltyPoints() { return loyaltyPoints; }
    public void setLoyaltyPoints(int loyaltyPoints) { this.loyaltyPoints = loyaltyPoints; }

    public boolean isHasMembership() { return hasMembership; }
    public void setHasMembership(boolean hasMembership) { this.hasMembership = hasMembership; }

    public String getMembershipTier() { return membershipTier; }
    public void setMembershipTier(String membershipTier) { this.membershipTier = membershipTier; }

    public LocalDate getMembershipStartDate() { return membershipStartDate; }
    public void setMembershipStartDate(LocalDate membershipStartDate) { this.membershipStartDate = membershipStartDate; }

    public LocalDate getMembershipExpiresAt() { return membershipExpiresAt; }
    public void setMembershipExpiresAt(LocalDate membershipExpiresAt) { this.membershipExpiresAt = membershipExpiresAt; }

    public String getMembershipStatus() { return membershipStatus; }
    public void setMembershipStatus(String membershipStatus) { this.membershipStatus = membershipStatus; }

    public List<AppointmentEntity> getAppointments() { return appointments; }
    public void setAppointments(List<AppointmentEntity> appointments) { this.appointments = appointments; }

    public List<InvoiceEntity> getInvoices() { return invoices; }
    public void setInvoices(List<InvoiceEntity> invoices) { this.invoices = invoices; }

    public List<CustomerMembershipEntity> getMemberships() { return memberships; }
    public void setMemberships(List<CustomerMembershipEntity> memberships) { this.memberships = memberships; }
}

