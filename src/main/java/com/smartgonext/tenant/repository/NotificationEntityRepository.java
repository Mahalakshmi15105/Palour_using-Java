package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationEntityRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findByIsRead(Boolean isRead);
    List<NotificationEntity> findByCategory(String category);
}
