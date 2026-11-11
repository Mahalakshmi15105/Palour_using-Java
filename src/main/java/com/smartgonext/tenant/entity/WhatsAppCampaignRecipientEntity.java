package com.smartgonext.tenant.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "whatsapp_campaign_recipients")
public class WhatsAppCampaignRecipientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private WhatsAppCampaignEntity campaign;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @Column(name = "customer_phone", length = 25)
    private String customerPhone;

    @Column(nullable = false, length = 30)
    private String status = "PENDING"; // "SENT", "DELIVERED", "READ", "FAILED", "PENDING"

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    public WhatsAppCampaignRecipientEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public WhatsAppCampaignEntity getCampaign() { return campaign; }
    public void setCampaign(WhatsAppCampaignEntity campaign) { this.campaign = campaign; }

    public CustomerEntity getCustomer() { return customer; }
    public void setCustomer(CustomerEntity customer) { this.customer = customer; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
}
