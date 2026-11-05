package com.smartgonext.model;

public class Customer {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String membershipTier; // "Gold", "Platinum", "Silver", "Walk-in"
    private int loyaltyPoints;
    private int visitsCount;
    private double totalSpent;
    private String lastVisit;

    public Customer() {}

    public Customer(String id, String name, String phone, String email, String membershipTier, 
                    int loyaltyPoints, int visitsCount, double totalSpent, String lastVisit) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.membershipTier = membershipTier;
        this.loyaltyPoints = loyaltyPoints;
        this.visitsCount = visitsCount;
        this.totalSpent = totalSpent;
        this.lastVisit = lastVisit;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMembershipTier() { return membershipTier; }
    public void setMembershipTier(String membershipTier) { this.membershipTier = membershipTier; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public void setLoyaltyPoints(int loyaltyPoints) { this.loyaltyPoints = loyaltyPoints; }
    public int getVisitsCount() { return visitsCount; }
    public void setVisitsCount(int visitsCount) { this.visitsCount = visitsCount; }
    public double getTotalSpent() { return totalSpent; }
    public void setTotalSpent(double totalSpent) { this.totalSpent = totalSpent; }
    public String getLastVisit() { return lastVisit; }
    public void setLastVisit(String lastVisit) { this.lastVisit = lastVisit; }
}

