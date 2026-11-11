package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findByBranchId(Long branchId);
    List<EmployeeEntity> findByStatus(String status);
    Optional<EmployeeEntity> findByPhone(String phone);
}
