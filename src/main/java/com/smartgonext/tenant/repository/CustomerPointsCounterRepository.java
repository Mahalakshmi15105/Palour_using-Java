package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.CustomerPointsCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerPointsCounterRepository extends JpaRepository<CustomerPointsCounter, Long> {
    Optional<CustomerPointsCounter> findByCustomerId(Long customerId);
}
