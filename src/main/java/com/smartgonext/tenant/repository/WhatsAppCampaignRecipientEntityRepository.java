package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.WhatsAppCampaignRecipientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhatsAppCampaignRecipientEntityRepository extends JpaRepository<WhatsAppCampaignRecipientEntity, Long> {
    List<WhatsAppCampaignRecipientEntity> findByCampaignId(Long campaignId);
    List<WhatsAppCampaignRecipientEntity> findByStatus(String status);
}
