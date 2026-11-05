package com.smartgonext.model;

import java.util.List;

public class MembershipTier {
    private String id;
    private String name;
    private double price;
    private int validityMonths;
    private int discountPercent;
    private List<String> benefits;
    private int memberCount;
    private String badgeColor;

    public MembershipTier() {}

    public MembershipTier(String id, String name, double price, int validityMonths, 
                          int discountPercent, List<String> benefits, int memberCount, String badgeColor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.validityMonths = validityMonths;
        this.discountPercent = discountPercent;
        this.benefits = benefits;
        this.memberCount = memberCount;
        this.badgeColor = badgeColor;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getValidityMonths() { return validityMonths; }
    public void setValidityMonths(int validityMonths) { this.validityMonths = validityMonths; }
    public int getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(int discountPercent) { this.discountPercent = discountPercent; }
    public List<String> getBenefits() { return benefits; }
    public void setBenefits(List<String> benefits) { this.benefits = benefits; }
    public int getMemberCount() { return memberCount; }
    public void setMemberCount(int memberCount) { this.memberCount = memberCount; }
    public String getBadgeColor() { return badgeColor; }
    public void setBadgeColor(String badgeColor) { this.badgeColor = badgeColor; }
}

