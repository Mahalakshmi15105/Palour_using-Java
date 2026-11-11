package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceEntityRepository extends JpaRepository<InvoiceEntity, Long> {
    Optional<InvoiceEntity> findByInvoiceNumber(String invoiceNumber);
    List<InvoiceEntity> findByBranchId(Long branchId);
    List<InvoiceEntity> findByStatus(String status);
    List<InvoiceEntity> findByCustomerIdOrderByCreatedAtDesc(Long customerId);
    long countByCustomerId(Long customerId);

    @Query("SELECT COALESCE(SUM(i.total), 0) FROM InvoiceEntity i WHERE i.customer.id = :customerId AND i.status = 'Paid'")
    BigDecimal calculateTotalSpending(@Param("customerId") Long customerId);
}
