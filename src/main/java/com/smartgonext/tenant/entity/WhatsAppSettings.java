package com.smartgonext.tenant.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "whatsapp_settings")
public class WhatsAppSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meta_waba_id", length = 100)
    private String metaWabaId;

    @Column(name = "meta_phone_number_id", length = 100)
    private String metaPhoneNumberId;

    @Column(name = "encrypted_access_token", columnDefinition = "TEXT")
    private String encryptedAccessToken;

    @Column(nullable = false, length = 30)
    private String status = "Inactive"; // "Active", "Inactive"

    public WhatsAppSettings() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMetaWabaId() { return metaWabaId; }
    public void setMetaWabaId(String metaWabaId) { this.metaWabaId = metaWabaId; }

    public String getMetaPhoneNumberId() { return metaPhoneNumberId; }
    public void setMetaPhoneNumberId(String metaPhoneNumberId) { this.metaPhoneNumberId = metaPhoneNumberId; }

    public String getEncryptedAccessToken() { return encryptedAccessToken; }
    public void setEncryptedAccessToken(String encryptedAccessToken) { this.encryptedAccessToken = encryptedAccessToken; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
