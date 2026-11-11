package com.smartgonext.tenant.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "tenant_settings")
public class TenantSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;

    @Column(name = "logo_url", length = 300)
    private String logoUrl;

    @Column(name = "gst_number", length = 50)
    private String gstNumber;

    @Column(name = "tax_rate", precision = 5, scale = 2)
    private BigDecimal taxRate;

    @Column(name = "invoice_prefix", length = 20)
    private String invoicePrefix;

    @Column(name = "receipt_template", length = 30)
    private String receiptTemplate; // "Classic", "80mm"

    @Column(name = "currency_symbol", length = 10)
    private String currencySymbol; // "₹", "$", "€"

    @Column(name = "primary_color", length = 20)
    private String primaryColor;

    @Column(name = "booking_enabled")
    private Boolean bookingEnabled = true;

    @Column(name = "working_days", length = 100)
    private String workingDays; // "Mon,Tue,Wed,Thu,Fri,Sat,Sun"

    @Column(name = "opening_time")
    private LocalTime openingTime;

    @Column(name = "closing_time")
    private LocalTime closingTime;

    @Column(name = "google_drive_access_token", columnDefinition = "TEXT")
    private String googleDriveAccessToken;

    public TenantSettings() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTenantId() { return tenantId; }
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    public String getGstNumber() { return gstNumber; }
    public void setGstNumber(String gstNumber) { this.gstNumber = gstNumber; }

    public BigDecimal getTaxRate() { return taxRate; }
    public void setTaxRate(BigDecimal taxRate) { this.taxRate = taxRate; }

    public String getInvoicePrefix() { return invoicePrefix; }
    public void setInvoicePrefix(String invoicePrefix) { this.invoicePrefix = invoicePrefix; }

    public String getReceiptTemplate() { return receiptTemplate; }
    public void setReceiptTemplate(String receiptTemplate) { this.receiptTemplate = receiptTemplate; }

    public String getCurrencySymbol() { return currencySymbol; }
    public void setCurrencySymbol(String currencySymbol) { this.currencySymbol = currencySymbol; }

    public String getPrimaryColor() { return primaryColor; }
    public void setPrimaryColor(String primaryColor) { this.primaryColor = primaryColor; }

    public Boolean getBookingEnabled() { return bookingEnabled; }
    public void setBookingEnabled(Boolean bookingEnabled) { this.bookingEnabled = bookingEnabled; }

    public String getWorkingDays() { return workingDays; }
    public void setWorkingDays(String workingDays) { this.workingDays = workingDays; }

    public LocalTime getOpeningTime() { return openingTime; }
    public void setOpeningTime(LocalTime openingTime) { this.openingTime = openingTime; }

    public LocalTime getClosingTime() { return closingTime; }
    public void setClosingTime(LocalTime closingTime) { this.closingTime = closingTime; }

    public String getGoogleDriveAccessToken() { return googleDriveAccessToken; }
    public void setGoogleDriveAccessToken(String googleDriveAccessToken) { this.googleDriveAccessToken = googleDriveAccessToken; }
}
