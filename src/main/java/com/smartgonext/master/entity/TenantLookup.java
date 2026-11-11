package com.smartgonext.master.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tenant_lookups", indexes = {
    @Index(name = "idx_tenant_lookups_email", columnList = "email", unique = true)
})
public class TenantLookup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(name = "db_name", nullable = false, length = 100)
    private String dbName;

    @Column(name = "db_connection_uri", length = 300)
    private String dbConnectionUri;

    @Column(length = 50)
    private String role; // "ParlourAdmin", "BranchAdmin", "Receptionist"

    public TenantLookup() {}

    public TenantLookup(String email, Tenant tenant, String dbName, String dbConnectionUri, String role) {
        this.email = email;
        this.tenant = tenant;
        this.dbName = dbName;
        this.dbConnectionUri = dbConnectionUri;
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Tenant getTenant() { return tenant; }
    public void setTenant(Tenant tenant) { this.tenant = tenant; }

    public String getDbName() { return dbName; }
    public void setDbName(String dbName) { this.dbName = dbName; }

    public String getDbConnectionUri() { return dbConnectionUri; }
    public void setDbConnectionUri(String dbConnectionUri) { this.dbConnectionUri = dbConnectionUri; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
