package com.smartgonext.tenant.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(length = 25)
    private String phone;

    @Column(length = 120)
    private String email;

    @Column(name = "is_main_branch", nullable = false)
    private Boolean isMainBranch = false;

    @Column(precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(name = "geofence_radius_meters")
    private Integer geofenceRadiusMeters;

    @Column(name = "wifi_ssid", length = 100)
    private String wifiSsid;

    @Column(name = "wifi_public_ip", length = 50)
    private String wifiPublicIp;

    @Column(name = "initial_opening_balance", precision = 10, scale = 2)
    private BigDecimal initialOpeningBalance;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.isMainBranch == null) this.isMainBranch = false;
        if (this.initialOpeningBalance == null) this.initialOpeningBalance = BigDecimal.ZERO;
    }

    public Branch() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTenantId() { return tenantId; }
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean getIsMainBranch() { return isMainBranch; }
    public void setIsMainBranch(Boolean isMainBranch) { this.isMainBranch = isMainBranch; }

    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }

    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }

    public Integer getGeofenceRadiusMeters() { return geofenceRadiusMeters; }
    public void setGeofenceRadiusMeters(Integer geofenceRadiusMeters) { this.geofenceRadiusMeters = geofenceRadiusMeters; }

    public String getWifiSsid() { return wifiSsid; }
    public void setWifiSsid(String wifiSsid) { this.wifiSsid = wifiSsid; }

    public String getWifiPublicIp() { return wifiPublicIp; }
    public void setWifiPublicIp(String wifiPublicIp) { this.wifiPublicIp = wifiPublicIp; }

    public BigDecimal getInitialOpeningBalance() { return initialOpeningBalance; }
    public void setInitialOpeningBalance(BigDecimal initialOpeningBalance) { this.initialOpeningBalance = initialOpeningBalance; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
