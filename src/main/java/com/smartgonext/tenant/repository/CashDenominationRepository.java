package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.CashDenomination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CashDenominationRepository extends JpaRepository<CashDenomination, Long> {
    Optional<CashDenomination> findByBranchIdAndDate(Long branchId, LocalDate date);
}

