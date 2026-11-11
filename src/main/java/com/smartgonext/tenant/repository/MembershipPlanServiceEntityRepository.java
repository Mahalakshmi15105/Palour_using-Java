package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.MembershipPlanServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipPlanServiceEntityRepository extends JpaRepository<MembershipPlanServiceEntity, Long> {
    List<MembershipPlanServiceEntity> findByMembershipPlanId(Long membershipPlanId);
}

