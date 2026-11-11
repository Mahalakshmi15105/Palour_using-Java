package com.smartgonext.config.multitenancy;

import com.smartgonext.config.security.CustomUserDetails;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 50)
public class TenantFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(TenantFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest) {
            String resolvedTenant = null;
            HttpSession session = httpRequest.getSession(false);

            // 1. Priority: Check authenticated user principal
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            boolean isSuperAdmin = false;
            String authenticatedTenant = null;

            if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof CustomUserDetails userDetails) {
                isSuperAdmin = userDetails.isSuperAdmin();
                authenticatedTenant = userDetails.getTenantId();
            } else if (session != null) {
                authenticatedTenant = (String) session.getAttribute("CURRENT_TENANT_ID");
                String userRole = (String) session.getAttribute("LOGGED_IN_USER_ROLE");
                isSuperAdmin = "SUPER_ADMIN".equalsIgnoreCase(userRole);
            }

            // 2. Strict Isolation: If an authenticated tenant user is present, LOCK to their assigned tenant!
            // Do NOT permit header or parameter overrides for non-SuperAdmin users!
            if (authenticatedTenant != null && !authenticatedTenant.isBlank()) {
                if (!isSuperAdmin) {
                    resolvedTenant = authenticatedTenant;
                } else {
                    // SuperAdmin may switch if explicitly requested via parameter or header
                    String requestedTenant = httpRequest.getHeader("X-Tenant-ID");
                    if (requestedTenant == null || requestedTenant.isBlank()) {
                        requestedTenant = httpRequest.getParameter("tenant");
                    }
                    if (requestedTenant != null && !requestedTenant.isBlank() && isValidTenantIdentifier(requestedTenant)) {
                        resolvedTenant = requestedTenant.trim();
                    } else {
                        resolvedTenant = authenticatedTenant;
                    }
                }
            } else {
                // Unauthenticated request (e.g. login page, static assets)
                String requestedTenant = httpRequest.getHeader("X-Tenant-ID");
                if (requestedTenant == null || requestedTenant.isBlank()) {
                    requestedTenant = httpRequest.getParameter("tenant");
                }
                if (requestedTenant != null && !requestedTenant.isBlank() && isValidTenantIdentifier(requestedTenant)) {
                    resolvedTenant = requestedTenant.trim();
                } else {
                    resolvedTenant = TenantContext.DEFAULT_TENANT;
                }
            }

            TenantContext.setTenantId(resolvedTenant);
        }

        try {
            chain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }

    private boolean isValidTenantIdentifier(String tenant) {
        return tenant != null && tenant.matches("^[a-zA-Z0-9_]{3,50}$");
    }
}
