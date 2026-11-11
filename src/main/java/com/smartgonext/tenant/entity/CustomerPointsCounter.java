package com.smartgonext.tenant.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_points_counters")
public class CustomerPointsCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false, unique = true)
    private CustomerEntity customer;

    @Column(name = "current_points_balance", nullable = false)
    private Integer currentPointsBalance = 0;

    @Column(name = "total_points_earned", nullable = false)
    private Integer totalPointsEarned = 0;

    @Column(name = "total_points_redeemed", nullable = false)
    private Integer totalPointsRedeemed = 0;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @PrePersist
    @PreUpdate
    protected void onSave() {
        this.lastUpdated = LocalDateTime.now();
    }

    public CustomerPointsCounter() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CustomerEntity getCustomer() { return customer; }
    public void setCustomer(CustomerEntity customer) { this.customer = customer; }

    public Integer getCurrentPointsBalance() { return currentPointsBalance; }
    public void setCurrentPointsBalance(Integer currentPointsBalance) { this.currentPointsBalance = currentPointsBalance; }

    public Integer getTotalPointsEarned() { return totalPointsEarned; }
    public void setTotalPointsEarned(Integer totalPointsEarned) { this.totalPointsEarned = totalPointsEarned; }

    public Integer getTotalPointsRedeemed() { return totalPointsRedeemed; }
    public void setTotalPointsRedeemed(Integer totalPointsRedeemed) { this.totalPointsRedeemed = totalPointsRedeemed; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
