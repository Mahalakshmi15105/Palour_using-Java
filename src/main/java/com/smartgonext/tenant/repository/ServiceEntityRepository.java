package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findByCategoryId(Long categoryId);
    List<ServiceEntity> findByStatus(String status);
}
