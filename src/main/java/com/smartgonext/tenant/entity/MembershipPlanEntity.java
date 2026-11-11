package com.smartgonext.tenant.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "membership_plans")
public class MembershipPlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name; // "Silver Elite", "Gold Card", "Platinum VIP", "Bridal Privilege"

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "validity_months", nullable = false)
    private Integer validityMonths;

    @Column(name = "service_discount_percent", precision = 5, scale = 2)
    private BigDecimal serviceDiscountPercent = BigDecimal.ZERO;

    @Column(name = "product_discount_percent", precision = 5, scale = 2)
    private BigDecimal productDiscountPercent = BigDecimal.ZERO;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 30)
    private String status = "Active";

    public MembershipPlanEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getValidityMonths() { return validityMonths; }
    public void setValidityMonths(Integer validityMonths) { this.validityMonths = validityMonths; }

    public BigDecimal getServiceDiscountPercent() { return serviceDiscountPercent; }
    public void setServiceDiscountPercent(BigDecimal serviceDiscountPercent) { this.serviceDiscountPercent = serviceDiscountPercent; }

    public BigDecimal getProductDiscountPercent() { return productDiscountPercent; }
    public void setProductDiscountPercent(BigDecimal productDiscountPercent) { this.productDiscountPercent = productDiscountPercent; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
