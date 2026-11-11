package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByPhone(String phone);
    Optional<CustomerEntity> findByEmail(String email);
}
