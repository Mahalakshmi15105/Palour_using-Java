package com.smartgonext.tenant.repository;

import com.smartgonext.tenant.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByPhone(String phone);

    Optional<CustomerEntity> findByEmail(String email);

    Optional<CustomerEntity> findByIdAndDeletedFalse(Long id);

    boolean existsByPhoneAndDeletedFalse(String phone);

    boolean existsByPhoneAndIdNotAndDeletedFalse(String phone, Long id);

    Page<CustomerEntity> findByDeletedFalse(Pageable pageable);

    @Query("SELECT c FROM CustomerEntity c WHERE c.deleted = false " +
           "AND (:keyword IS NULL OR :keyword = '' " +
           "     OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "     OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "     OR c.phone LIKE CONCAT('%', :keyword, '%') " +
           "     OR LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "AND (:gender IS NULL OR :gender = '' OR c.gender = :gender) " +
           "AND (:status IS NULL OR :status = '' OR c.status = :status)")
    Page<CustomerEntity> searchCustomers(@Param("keyword") String keyword,
                                         @Param("gender") String gender,
                                         @Param("status") String status,
                                         Pageable pageable);

    long countByDeletedFalse();

    long countByDeletedFalseAndStatus(String status);

    long countByDeletedFalseAndCreatedAtGreaterThanEqual(LocalDateTime startOfMonth);
}
