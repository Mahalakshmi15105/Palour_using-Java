package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceEntityRepository extends JpaRepository<InvoiceEntity, Long> {
    Optional<InvoiceEntity> findByInvoiceNumber(String invoiceNumber);
    List<InvoiceEntity> findByBranchId(Long branchId);
    List<InvoiceEntity> findByStatus(String status);
}
