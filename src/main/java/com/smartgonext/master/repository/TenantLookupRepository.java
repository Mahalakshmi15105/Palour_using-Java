package com.smartgonext.master.repository;

import com.smartgonext.master.entity.TenantLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantLookupRepository extends JpaRepository<TenantLookup, Long> {
    Optional<TenantLookup> findByEmail(String email);
}
