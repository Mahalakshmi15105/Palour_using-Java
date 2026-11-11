package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.CustomerVisitCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerVisitCounterRepository extends JpaRepository<CustomerVisitCounter, Long> {
    Optional<CustomerVisitCounter> findByCustomerId(Long customerId);

    @Query("SELECT COUNT(v) FROM CustomerVisitCounter v WHERE v.currentVisitCount > 1")
    long countReturningCustomers();
}
