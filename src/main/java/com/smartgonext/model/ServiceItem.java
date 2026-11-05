package com.smartgonext.model;

public class ServiceItem {
    private String id;
    private String name;
    private String category; // "Hair", "Skin & Facial", "Spa & Massage", "Nails & Bridal"
    private int durationMinutes;
    private double price;
    private String description;
    private boolean isAvailable;

    public ServiceItem() {}

    public ServiceItem(String id, String name, String category, int durationMinutes, double price, String description, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.durationMinutes = durationMinutes;
        this.price = price;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}

