package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.InvoicePaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoicePaymentEntityRepository extends JpaRepository<InvoicePaymentEntity, Long> {
    List<InvoicePaymentEntity> findByInvoiceId(Long invoiceId);
}

