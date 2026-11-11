package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.PayrollAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PayrollAdjustmentRepository extends JpaRepository<PayrollAdjustment, Long> {
    List<PayrollAdjustment> findByEmployeeId(Long employeeId);
    List<PayrollAdjustment> findByDate(LocalDate date);
}

