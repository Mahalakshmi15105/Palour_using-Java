package com.smartgonext.model;

public class NotificationItem {
    private String id;
    private String title;
    private String message;
    private String category; // "Appointment", "Inventory", "System", "Billing"
    private String timeAgo;
    private boolean isRead;
    private String priority; // "High", "Normal", "Info"

    public NotificationItem() {}

    public NotificationItem(String id, String title, String message, String category, String timeAgo, boolean isRead, String priority) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.category = category;
        this.timeAgo = timeAgo;
        this.isRead = isRead;
        this.priority = priority;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getTimeAgo() { return timeAgo; }
    public void setTimeAgo(String timeAgo) { this.timeAgo = timeAgo; }
    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
}

