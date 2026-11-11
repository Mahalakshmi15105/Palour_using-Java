package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.InvoiceLineItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceLineItemEntityRepository extends JpaRepository<InvoiceLineItemEntity, Long> {
    List<InvoiceLineItemEntity> findByInvoiceId(Long invoiceId);
}

