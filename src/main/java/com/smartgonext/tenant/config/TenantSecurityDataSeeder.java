package com.smartgonext.tenant.config;

import com.smartgonext.config.multitenancy.TenantContext;
import com.smartgonext.config.security.UserRole;
import com.smartgonext.tenant.entity.Branch;
import com.smartgonext.tenant.entity.TenantUser;
import com.smartgonext.tenant.repository.BranchRepository;
import com.smartgonext.tenant.repository.TenantUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(10)
public class TenantSecurityDataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(TenantSecurityDataSeeder.class);

    private final TenantUserRepository userRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;

    public TenantSecurityDataSeeder(TenantUserRepository userRepository,
                                    BranchRepository branchRepository,
                                    PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String previousTenant = TenantContext.getTenantId();
        try {
            TenantContext.setTenantId(TenantContext.DEFAULT_TENANT);
            log.info("Checking and upgrading tenant users to BCrypt password hashes in {}", TenantContext.DEFAULT_TENANT);

            Branch defaultBranch = branchRepository.findAll().stream().findFirst().orElse(null);

            // 1. Parlour Admin
            updateOrCreateUser(
                    "admin@123.com",
                    "Mahalakshmi",
                    "admin123",
                    UserRole.PARLOUR_ADMIN,
                    defaultBranch
            );

            // 2. Branch Admin
            updateOrCreateUser(
                    "branchadmin@123.com",
                    "Priya Sharma",
                    "staff123",
                    UserRole.BRANCH_ADMIN,
                    defaultBranch
            );

            // 3. Employee
            updateOrCreateUser(
                    "employee@123.com",
                    "Ananya Verma",
                    "staff123",
                    UserRole.EMPLOYEE,
                    defaultBranch
            );

            log.info("Tenant user security synchronization complete.");
        } catch (Exception ex) {
            log.error("Error during tenant security seeding: {}", ex.getMessage(), ex);
        } finally {
            if (previousTenant != null) {
                TenantContext.setTenantId(previousTenant);
            } else {
                TenantContext.clear();
            }
        }
    }

    private void updateOrCreateUser(String email, String fullName, String rawPassword,
                                    UserRole role, Branch branch) {
        Optional<TenantUser> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            TenantUser user = userOpt.get();
            boolean changed = false;

            if (!role.name().equalsIgnoreCase(user.getRole())) {
                user.setRole(role.name());
                changed = true;
            }

            if (user.getPasswordHash() == null ||
                (!user.getPasswordHash().startsWith("$2a$") && !user.getPasswordHash().startsWith("$2b$"))) {
                user.setPasswordHash(passwordEncoder.encode(rawPassword));
                changed = true;
            }

            if (user.getBranch() == null && branch != null) {
                user.setBranch(branch);
                changed = true;
            }

            if (changed) {
                userRepository.save(user);
                log.info("Upgraded existing tenant user: {} with role {} and BCrypt hash", email, role);
            }
        } else {
            TenantUser newUser = new TenantUser();
            newUser.setTenantId(1L);
            newUser.setEmail(email);
            newUser.setFullName(fullName);
            newUser.setPasswordHash(passwordEncoder.encode(rawPassword));
            newUser.setRole(role.name());
            newUser.setStatus("ACTIVE");
            newUser.setBranch(branch);
            userRepository.save(newUser);
            log.info("Created new tenant user: {} with role {} and BCrypt hash", email, role);
        }
    }
}
