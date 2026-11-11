package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceEntityRepository extends JpaRepository<AttendanceEntity, Long> {
    List<AttendanceEntity> findByBranchId(Long branchId);
    List<AttendanceEntity> findByEmployeeId(Long employeeId);
}
