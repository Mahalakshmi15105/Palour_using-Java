package com.smartgonext.tenant.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "visit_membership_settings")
public class VisitMembershipSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visits_required", nullable = false)
    private Integer visitsRequired; // e.g. after every 6 visits

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reward_service_id", nullable = false)
    private ServiceEntity rewardService; // 1 free haircut / facial

    @Column(nullable = false, length = 30)
    private String status = "Active";

    public VisitMembershipSetting() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getVisitsRequired() { return visitsRequired; }
    public void setVisitsRequired(Integer visitsRequired) { this.visitsRequired = visitsRequired; }

    public ServiceEntity getRewardService() { return rewardService; }
    public void setRewardService(ServiceEntity rewardService) { this.rewardService = rewardService; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
