package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findByCustomerId(Long customerId);
    List<Reminder> findByReminderDate(LocalDate reminderDate);
    List<Reminder> findByStatus(String status);
}
