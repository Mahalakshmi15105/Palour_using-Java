package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.MembershipBenefitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipBenefitEntityRepository extends JpaRepository<MembershipBenefitEntity, Long> {
    List<MembershipBenefitEntity> findByCustomerMembershipId(Long customerMembershipId);
}
