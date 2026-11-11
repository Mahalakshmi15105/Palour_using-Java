package com.smartgonext.tenant.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "membership_plan_services")
public class MembershipPlanServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_plan_id", nullable = false)
    private MembershipPlanEntity membershipPlan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity service;

    @Column(name = "discount_percent", precision = 5, scale = 2)
    private BigDecimal discountPercent;

    public MembershipPlanServiceEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public MembershipPlanEntity getMembershipPlan() { return membershipPlan; }
    public void setMembershipPlan(MembershipPlanEntity membershipPlan) { this.membershipPlan = membershipPlan; }

    public ServiceEntity getService() { return service; }
    public void setService(ServiceEntity service) { this.service = service; }

    public BigDecimal getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(BigDecimal discountPercent) { this.discountPercent = discountPercent; }
}
