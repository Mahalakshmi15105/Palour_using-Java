package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity, Long> {
    Optional<AppointmentEntity> findByAppointmentNumber(String appointmentNumber);
    List<AppointmentEntity> findByAppointmentDate(LocalDate appointmentDate);
    List<AppointmentEntity> findByStatus(String status);
}
