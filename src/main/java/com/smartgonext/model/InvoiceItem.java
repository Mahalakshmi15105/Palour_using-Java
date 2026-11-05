package com.smartgonext.model;

public class InvoiceItem {
    private String invoiceNumber;
    private String customerName;
    private String date;
    private String services;
    private double totalAmount;
    private String paymentMethod; // "UPI", "Credit Card", "Cash"
    private String status; // "Paid", "Pending"

    public InvoiceItem() {}

    public InvoiceItem(String invoiceNumber, String customerName, String date, String services, 
                       double totalAmount, String paymentMethod, String status) {
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.date = date;
        this.services = services;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getServices() { return services; }
    public void setServices(String services) { this.services = services; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

