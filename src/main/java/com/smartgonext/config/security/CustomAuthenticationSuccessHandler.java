package com.smartgonext.config.security;

import com.smartgonext.config.multitenancy.TenantContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession(true);

        if (authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            session.setAttribute("CURRENT_TENANT_ID", userDetails.getTenantId());
            session.setAttribute("LOGGED_IN_USER_ID", userDetails.getUserId());
            session.setAttribute("LOGGED_IN_USER_NAME", userDetails.getFullName());
            session.setAttribute("LOGGED_IN_USER_EMAIL", userDetails.getUsername());
            session.setAttribute("LOGGED_IN_USER_ROLE", userDetails.getRole().name());
            session.setAttribute("LOGGED_IN_USER_ROLE_DISPLAY", userDetails.getRole().getDisplayName());
            session.setAttribute("LOGGED_IN_BRANCH_ID", userDetails.getBranchId());
            session.setAttribute("LOGGED_IN_BRANCH_NAME", userDetails.getBranchName() != null ? userDetails.getBranchName() : "All Branches");

            TenantContext.setTenantId(userDetails.getTenantId());
            log.info("User {} (Role: {}) successfully logged in. Bound to tenant DB: {}",
                    userDetails.getUsername(), userDetails.getRole(), userDetails.getTenantId());
        }

        response.sendRedirect(request.getContextPath() + "/dashboard");
    }
}

