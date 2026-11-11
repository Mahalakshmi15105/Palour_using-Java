package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.CustomerMembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerMembershipEntityRepository extends JpaRepository<CustomerMembershipEntity, Long> {
    List<CustomerMembershipEntity> findByCustomerId(Long customerId);
    List<CustomerMembershipEntity> findByStatus(String status);
    List<CustomerMembershipEntity> findByCustomerIdOrderByStartDateDesc(Long customerId);
    Optional<CustomerMembershipEntity> findFirstByCustomerIdAndStatusOrderByStartDateDesc(Long customerId, String status);
}
