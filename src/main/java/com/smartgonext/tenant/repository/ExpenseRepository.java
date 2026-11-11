package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByBranchId(Long branchId);
    List<Expense> findByBranchIdAndDate(Long branchId, LocalDate date);
}
