package com.smartgonext.model;

public class CampaignItem {
    private String id;
    private String title;
    private String targetAudience;
    private int recipientsCount;
    private String deliveryRate;
    private String status; // "Sent", "Scheduled", "Draft"
    private String date;
    private String templatePreview;

    public CampaignItem() {}

    public CampaignItem(String id, String title, String targetAudience, int recipientsCount, 
                        String deliveryRate, String status, String date, String templatePreview) {
        this.id = id;
        this.title = title;
        this.targetAudience = targetAudience;
        this.recipientsCount = recipientsCount;
        this.deliveryRate = deliveryRate;
        this.status = status;
        this.date = date;
        this.templatePreview = templatePreview;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getTargetAudience() { return targetAudience; }
    public void setTargetAudience(String targetAudience) { this.targetAudience = targetAudience; }
    public int getRecipientsCount() { return recipientsCount; }
    public void setRecipientsCount(int recipientsCount) { this.recipientsCount = recipientsCount; }
    public String getDeliveryRate() { return deliveryRate; }
    public void setDeliveryRate(String deliveryRate) { this.deliveryRate = deliveryRate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTemplatePreview() { return templatePreview; }
    public void setTemplatePreview(String templatePreview) { this.templatePreview = templatePreview; }
}

