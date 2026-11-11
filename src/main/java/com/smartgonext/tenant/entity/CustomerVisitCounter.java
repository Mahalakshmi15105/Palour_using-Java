package com.smartgonext.tenant.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customer_visit_counters")
public class CustomerVisitCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false, unique = true)
    private CustomerEntity customer;

    @Column(name = "current_visit_count", nullable = false)
    private Integer currentVisitCount = 0;

    @Column(name = "total_free_services_claimed", nullable = false)
    private Integer totalFreeServicesClaimed = 0;

    @Column(name = "last_visit_date")
    private LocalDate lastVisitDate;

    public CustomerVisitCounter() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CustomerEntity getCustomer() { return customer; }
    public void setCustomer(CustomerEntity customer) { this.customer = customer; }

    public Integer getCurrentVisitCount() { return currentVisitCount; }
    public void setCurrentVisitCount(Integer currentVisitCount) { this.currentVisitCount = currentVisitCount; }

    public Integer getTotalFreeServicesClaimed() { return totalFreeServicesClaimed; }
    public void setTotalFreeServicesClaimed(Integer totalFreeServicesClaimed) { this.totalFreeServicesClaimed = totalFreeServicesClaimed; }

    public LocalDate getLastVisitDate() { return lastVisitDate; }
    public void setLastVisitDate(LocalDate lastVisitDate) { this.lastVisitDate = lastVisitDate; }
}
