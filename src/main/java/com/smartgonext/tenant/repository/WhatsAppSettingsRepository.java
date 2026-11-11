package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.WhatsAppSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WhatsAppSettingsRepository extends JpaRepository<WhatsAppSettings, Long> {
    Optional<WhatsAppSettings> findByStatus(String status);
}

