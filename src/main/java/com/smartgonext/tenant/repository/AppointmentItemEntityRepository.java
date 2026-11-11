package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.AppointmentItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentItemEntityRepository extends JpaRepository<AppointmentItemEntity, Long> {
    List<AppointmentItemEntity> findByAppointmentId(Long appointmentId);
}

