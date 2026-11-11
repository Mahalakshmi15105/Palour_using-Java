package com.smartgonext.master.config;

import com.smartgonext.master.entity.*;
import com.smartgonext.master.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class MasterDataSeeder implements CommandLineRunner {

    private final SubscriptionPlanRepository planRepository;
    private final TenantRepository tenantRepository;
    private final TenantLookupRepository lookupRepository;
    private final MasterUserRepository masterUserRepository;
    private final PlatformSettingRepository settingRepository;

    public MasterDataSeeder(SubscriptionPlanRepository planRepository,
                            TenantRepository tenantRepository,
                            TenantLookupRepository lookupRepository,
                            MasterUserRepository masterUserRepository,
                            PlatformSettingRepository settingRepository) {
        this.planRepository = planRepository;
        this.tenantRepository = tenantRepository;
        this.lookupRepository = lookupRepository;
        this.masterUserRepository = masterUserRepository;
        this.settingRepository = settingRepository;
    }

    @Override
    public void run(String... args) {
        seedPlans();
        seedSuperAdmin();
        seedDefaultTenant();
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
        if (masterUserRepository.count() == 0) {
            masterUserRepository.save(new MasterUser(
                    "superadmin@smartgonext.com",
                    "admin123", // In Phase 2 Step 3, BCrypt will hash this
                    "SaaS Platform Administrator",
                    "SUPER_ADMIN",
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

            lookupRepository.save(new TenantLookup(
                    "admin@smartgonext.com",
                    defaultTenant,
                    "parlour_java",
                    "jdbc:mysql://localhost:3306/parlour_java",
                    "ParlourAdmin"
            ));
        }
    }

    private void seedPlatformSettings() {
        if (settingRepository.count() == 0) {
            settingRepository.save(new PlatformSetting("PLATFORM_NAME", "SmartGoNext SaaS", "Global application name"));
            settingRepository.save(new PlatformSetting("SUPPORT_EMAIL", "support@smartgonext.com", "Master platform support desk"));
            settingRepository.save(new PlatformSetting("DEFAULT_CURRENCY", "USD", "Default currency symbol code"));
            settingRepository.save(new PlatformSetting("MAINTENANCE_MODE", "false", "Global maintenance lock"));
        }
    }
}
