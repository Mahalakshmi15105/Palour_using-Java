package com.smartgonext.master.config;

import com.smartgonext.config.security.UserRole;
import com.smartgonext.master.entity.*;
import com.smartgonext.master.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class MasterDataSeeder implements CommandLineRunner {

    private final SubscriptionPlanRepository planRepository;
    private final TenantRepository tenantRepository;
    private final TenantLookupRepository lookupRepository;
    private final MasterUserRepository masterUserRepository;
    private final PlatformSettingRepository settingRepository;
    private final PasswordEncoder passwordEncoder;

    public MasterDataSeeder(SubscriptionPlanRepository planRepository,
                            TenantRepository tenantRepository,
                            TenantLookupRepository lookupRepository,
                            MasterUserRepository masterUserRepository,
                            PlatformSettingRepository settingRepository,
                            PasswordEncoder passwordEncoder) {
        this.planRepository = planRepository;
        this.tenantRepository = tenantRepository;
        this.lookupRepository = lookupRepository;
        this.masterUserRepository = masterUserRepository;
        this.settingRepository = settingRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seedPlans();
        seedSuperAdmin();
        seedDefaultTenant();
        seedTenantLookups();
        seedPlatformSettings();
    }

    private void seedPlans() {
        if (planRepository.count() == 0) {
            planRepository.save(new SubscriptionPlan("Basic Plan", new BigDecimal("49.00"), 12, 5, 20, 500, 1, "Single studio parlour package"));
            planRepository.save(new SubscriptionPlan("Pro Salon Tier", new BigDecimal("99.00"), 12, 15, 60, 2000, 3, "Multi-branch salon management with WhatsApp marketing"));
            planRepository.save(new SubscriptionPlan("Enterprise Luxury", new BigDecimal("199.00"), 12, 50, 200, 10000, 10, "Full luxury chain suite with multi-tenant custom branding"));
        }
    }

    private void seedSuperAdmin() {
        Optional<MasterUser> existingOpt = masterUserRepository.findByEmail("superadmin@smartgonext.com");
        if (existingOpt.isPresent()) {
            MasterUser existing = existingOpt.get();
            // Ensure BCrypt hashed password
            if (!existing.getPasswordHash().startsWith("$2a$") && !existing.getPasswordHash().startsWith("$2b$")) {
                existing.setPasswordHash(passwordEncoder.encode(existing.getPasswordHash()));
                masterUserRepository.save(existing);
            }
        } else {
            masterUserRepository.save(new MasterUser(
                    "superadmin@smartgonext.com",
                    passwordEncoder.encode("admin123"),
                    "SaaS Platform Administrator",
                    UserRole.SUPER_ADMIN.name(),
                    "ACTIVE"
            ));
        }
    }

    private void seedDefaultTenant() {
        if (tenantRepository.count() == 0) {
            SubscriptionPlan proPlan = planRepository.findByName("Pro Salon Tier").orElse(null);
            Tenant defaultTenant = new Tenant(
                    "Downtown Flagship Lounge",
                    "admin@smartgonext.com",
                    "+1 (555) 789-2233",
                    proPlan,
                    LocalDate.now().plusYears(1),
                    "ACTIVE",
                    "parlour_java",
                    "jdbc:mysql://localhost:3306/parlour_java"
            );
            tenantRepository.save(defaultTenant);
        }
    }

    private void seedTenantLookups() {
        Tenant defaultTenant = tenantRepository.findByEmail("admin@smartgonext.com").orElse(null);
        if (defaultTenant == null && tenantRepository.count() > 0) {
            defaultTenant = tenantRepository.findAll().get(0);
        }
        if (defaultTenant != null) {
            syncLookup("admin@smartgonext.com", defaultTenant, "parlour_java", UserRole.PARLOUR_ADMIN.name());
            syncLookup("priya.sharma@smartgonext.com", defaultTenant, "parlour_java", UserRole.BRANCH_ADMIN.name());
            syncLookup("employee@smartgonext.com", defaultTenant, "parlour_java", UserRole.EMPLOYEE.name());
            syncLookup("reception@smartgonext.com", defaultTenant, "parlour_java", UserRole.EMPLOYEE.name());
        }
    }

    private void syncLookup(String email, Tenant tenant, String dbName, String role) {
        Optional<TenantLookup> lookupOpt = lookupRepository.findByEmail(email);
        if (lookupOpt.isEmpty()) {
            lookupRepository.save(new TenantLookup(
                    email,
                    tenant,
                    dbName,
                    "jdbc:mysql://localhost:3306/" + dbName,
                    role
            ));
        } else {
            TenantLookup lookup = lookupOpt.get();
            lookup.setRole(role);
            lookupRepository.save(lookup);
        }
    }

    private void seedPlatformSettings() {
        if (settingRepository.count() == 0) {
            settingRepository.save(new PlatformSetting("PLATFORM_NAME", "Parlour using java", "Global application name"));
            settingRepository.save(new PlatformSetting("SUPPORT_EMAIL", "support@parlour.com", "Master platform support desk"));
            settingRepository.save(new PlatformSetting("DEFAULT_CURRENCY", "USD", "Default currency symbol code"));
            settingRepository.save(new PlatformSetting("MAINTENANCE_MODE", "false", "Global maintenance lock"));
        } else {
            settingRepository.findBySettingKey("PLATFORM_NAME").ifPresent(setting -> {
                setting.setSettingValue("Parlour using java");
                settingRepository.save(setting);
            });
        }
    }
}
