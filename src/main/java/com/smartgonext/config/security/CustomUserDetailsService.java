package com.smartgonext.config.security;

import com.smartgonext.config.multitenancy.TenantContext;
import com.smartgonext.master.entity.MasterUser;
import com.smartgonext.master.entity.TenantLookup;
import com.smartgonext.master.repository.MasterUserRepository;
import com.smartgonext.master.repository.TenantLookupRepository;
import com.smartgonext.tenant.entity.TenantUser;
import com.smartgonext.tenant.repository.TenantUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final MasterUserRepository masterUserRepository;
    private final TenantLookupRepository tenantLookupRepository;
    private final TenantUserRepository tenantUserRepository;

    public CustomUserDetailsService(MasterUserRepository masterUserRepository,
                                    TenantLookupRepository tenantLookupRepository,
                                    TenantUserRepository tenantUserRepository) {
        this.masterUserRepository = masterUserRepository;
        this.tenantLookupRepository = tenantLookupRepository;
        this.tenantUserRepository = tenantUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.isBlank()) {
            throw new UsernameNotFoundException("Username/email cannot be empty");
        }
        String email = username.trim().toLowerCase();

        // 1. First check Master User (SuperAdmin)
        try {
            Optional<MasterUser> masterOpt = masterUserRepository.findByEmail(email);
            if (masterOpt.isPresent()) {
                MasterUser master = masterOpt.get();
                boolean isActive = "ACTIVE".equalsIgnoreCase(master.getStatus());
                log.info("Loaded master SuperAdmin user: {}", email);
                return new CustomUserDetails(
                        master.getId(),
                        master.getEmail(),
                        master.getPasswordHash(),
                        master.getFullName(),
                        UserRole.SUPER_ADMIN,
                        "parlour_java_master",
                        null,
                        null,
                        isActive
                );
            }
        } catch (Exception ex) {
            log.warn("Master user lookup check failed for {}: {}", email, ex.getMessage());
        }

        // 2. Check Tenant Lookups
        String targetTenantDb = TenantContext.DEFAULT_TENANT;
        try {
            Optional<TenantLookup> lookupOpt = tenantLookupRepository.findByEmail(email);
            if (lookupOpt.isPresent() && lookupOpt.get().getDbName() != null) {
                targetTenantDb = lookupOpt.get().getDbName();
            }
        } catch (Exception ex) {
            log.warn("Tenant lookup check failed for {}: {}", email, ex.getMessage());
        }

        // 3. Query Tenant Database for the operational user
        String previousTenant = TenantContext.getTenantId();
        try {
            TenantContext.setTenantId(targetTenantDb);
            Optional<TenantUser> tenantUserOpt = tenantUserRepository.findByEmail(email);
            if (tenantUserOpt.isPresent()) {
                TenantUser tUser = tenantUserOpt.get();
                boolean isActive = "ACTIVE".equalsIgnoreCase(tUser.getStatus());
                UserRole role = UserRole.fromString(tUser.getRole());
                Long branchId = (tUser.getBranch() != null) ? tUser.getBranch().getId() : null;
                String branchName = (tUser.getBranch() != null) ? tUser.getBranch().getName() : null;

                log.info("Loaded tenant user: {} with role {} in tenant DB {}", email, role, targetTenantDb);
                return new CustomUserDetails(
                        tUser.getId(),
                        tUser.getEmail(),
                        tUser.getPasswordHash(),
                        tUser.getFullName(),
                        role,
                        targetTenantDb,
                        branchId,
                        branchName,
                        isActive
                );
            }
        } catch (Exception ex) {
            log.error("Failed querying tenant database for user {}: {}", email, ex.getMessage());
        } finally {
            if (previousTenant != null) {
                TenantContext.setTenantId(previousTenant);
            } else {
                TenantContext.clear();
            }
        }

        throw new UsernameNotFoundException("No account found with email: " + email);
    }
}
