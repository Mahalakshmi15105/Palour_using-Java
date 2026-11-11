package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.StockReorderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockReorderLogRepository extends JpaRepository<StockReorderLog, Long> {
    List<StockReorderLog> findByProductId(Long productId);
    List<StockReorderLog> findBySupplierId(Long supplierId);
    List<StockReorderLog> findByStatus(String status);
}
