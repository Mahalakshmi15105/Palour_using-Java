package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.VisitMembershipSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitMembershipSettingRepository extends JpaRepository<VisitMembershipSetting, Long> {
    List<VisitMembershipSetting> findByStatus(String status);
}
