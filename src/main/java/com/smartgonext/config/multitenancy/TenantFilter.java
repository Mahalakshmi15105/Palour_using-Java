package com.smartgonext.config.multitenancy;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest) {
            String tenantId = httpRequest.getHeader("X-Tenant-ID");

            if (tenantId == null || tenantId.isBlank()) {
                tenantId = httpRequest.getParameter("tenant");
            }

            if (tenantId == null || tenantId.isBlank()) {
                HttpSession session = httpRequest.getSession(false);
                if (session != null) {
                    tenantId = (String) session.getAttribute("CURRENT_TENANT_ID");
                }
            }

            if (tenantId != null && !tenantId.isBlank()) {
                TenantContext.setTenantId(tenantId);
            } else {
                TenantContext.setTenantId(TenantContext.DEFAULT_TENANT);
            }
        }

        try {
            chain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }
}
