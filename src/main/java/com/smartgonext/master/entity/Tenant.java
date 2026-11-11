package com.smartgonext.master.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(length = 25)
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subscription_plan_id")
    private SubscriptionPlan subscriptionPlan;

    @Column(name = "subscription_valid_until")
    private LocalDate subscriptionValidUntil;

    @Column(nullable = false, length = 30)
    private String status; // "ACTIVE", "TRIAL", "SUSPENDED"

    @Column(name = "db_name", nullable = false, length = 100)
    private String dbName;

    @Column(name = "db_connection_uri", length = 300)
    private String dbConnectionUri;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) this.status = "ACTIVE";
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Tenant() {}

    public Tenant(String name, String email, String phone, SubscriptionPlan subscriptionPlan, 
                  LocalDate subscriptionValidUntil, String status, String dbName, String dbConnectionUri) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.subscriptionPlan = subscriptionPlan;
        this.subscriptionValidUntil = subscriptionValidUntil;
        this.status = status;
        this.dbName = dbName;
        this.dbConnectionUri = dbConnectionUri;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public SubscriptionPlan getSubscriptionPlan() { return subscriptionPlan; }
    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) { this.subscriptionPlan = subscriptionPlan; }

    public LocalDate getSubscriptionValidUntil() { return subscriptionValidUntil; }
    public void setSubscriptionValidUntil(LocalDate subscriptionValidUntil) { this.subscriptionValidUntil = subscriptionValidUntil; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDbName() { return dbName; }
    public void setDbName(String dbName) { this.dbName = dbName; }

    public String getDbConnectionUri() { return dbConnectionUri; }
    public void setDbConnectionUri(String dbConnectionUri) { this.dbConnectionUri = dbConnectionUri; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
