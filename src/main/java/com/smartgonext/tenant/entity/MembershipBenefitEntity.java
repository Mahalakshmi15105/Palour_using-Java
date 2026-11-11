package com.smartgonext.tenant.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "membership_benefits")
public class MembershipBenefitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_membership_id", nullable = false)
    private CustomerMembershipEntity customerMembership;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity service;

    @Column(name = "total_quantity", nullable = false)
    private Integer totalQuantity;

    @Column(name = "remaining_quantity", nullable = false)
    private Integer remainingQuantity;

    public MembershipBenefitEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CustomerMembershipEntity getCustomerMembership() { return customerMembership; }
    public void setCustomerMembership(CustomerMembershipEntity customerMembership) { this.customerMembership = customerMembership; }

    public ServiceEntity getService() { return service; }
    public void setService(ServiceEntity service) { this.service = service; }

    public Integer getTotalQuantity() { return totalQuantity; }
    public void setTotalQuantity(Integer totalQuantity) { this.totalQuantity = totalQuantity; }

    public Integer getRemainingQuantity() { return remainingQuantity; }
    public void setRemainingQuantity(Integer remainingQuantity) { this.remainingQuantity = remainingQuantity; }
}
