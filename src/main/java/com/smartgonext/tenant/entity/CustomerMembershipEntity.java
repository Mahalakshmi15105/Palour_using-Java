package com.smartgonext.tenant.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_memberships")
public class CustomerMembershipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "membership_plan_id", nullable = false)
    private MembershipPlanEntity membershipPlan;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "expires_at", nullable = false)
    private LocalDate expiresAt;

    @Column(nullable = false, length = 30)
    private String status = "Active"; // "Active", "Expired", "Cancelled"

    @OneToMany(mappedBy = "customerMembership", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MembershipBenefitEntity> benefits = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.startDate == null) this.startDate = LocalDate.now();
    }

    public CustomerMembershipEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CustomerEntity getCustomer() { return customer; }
    public void setCustomer(CustomerEntity customer) { this.customer = customer; }

    public MembershipPlanEntity getMembershipPlan() { return membershipPlan; }
    public void setMembershipPlan(MembershipPlanEntity membershipPlan) { this.membershipPlan = membershipPlan; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDate expiresAt) { this.expiresAt = expiresAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<MembershipBenefitEntity> getBenefits() { return benefits; }
    public void setBenefits(List<MembershipBenefitEntity> benefits) { this.benefits = benefits; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
