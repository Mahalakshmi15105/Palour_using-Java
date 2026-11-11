package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.MembershipPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipPlanEntityRepository extends JpaRepository<MembershipPlanEntity, Long> {
    Optional<MembershipPlanEntity> findByName(String name);
}
