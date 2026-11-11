package com.smartgonext.tenant.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reminders")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id")
    private InvoiceEntity invoice;

    @Column(name = "reminder_type", nullable = false, length = 80)
    private String reminderType; // "Service Cycle", "Membership Renewal", "Post-Service Follow-up"

    @Column(name = "reminder_date", nullable = false)
    private LocalDate reminderDate;

    @Column(nullable = false, length = 30)
    private String status = "Pending"; // "Pending", "Done"

    @Column(columnDefinition = "TEXT")
    private String message;

    public Reminder() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CustomerEntity getCustomer() { return customer; }
    public void setCustomer(CustomerEntity customer) { this.customer = customer; }

    public InvoiceEntity getInvoice() { return invoice; }
    public void setInvoice(InvoiceEntity invoice) { this.invoice = invoice; }

    public String getReminderType() { return reminderType; }
    public void setReminderType(String reminderType) { this.reminderType = reminderType; }

    public LocalDate getReminderDate() { return reminderDate; }
    public void setReminderDate(LocalDate reminderDate) { this.reminderDate = reminderDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
