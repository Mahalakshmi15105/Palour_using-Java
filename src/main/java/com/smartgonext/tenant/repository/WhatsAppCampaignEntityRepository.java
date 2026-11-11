package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.WhatsAppCampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhatsAppCampaignEntityRepository extends JpaRepository<WhatsAppCampaignEntity, Long> {
    List<WhatsAppCampaignEntity> findByStatus(String status);
}

