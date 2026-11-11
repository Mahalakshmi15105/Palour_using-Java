package com.smartgonext.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final Long userId;
    private final String email;
    private final String password;
    private final String fullName;
    private final UserRole role;
    private final String tenantId;
    private final Long branchId;
    private final String branchName;
    private final boolean active;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetails(Long userId, String email, String password, String fullName,
                             UserRole role, String tenantId, Long branchId, String branchName, boolean active) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.fullName = fullName != null ? fullName : email;
        this.role = role != null ? role : UserRole.EMPLOYEE;
        this.tenantId = tenantId;
        this.branchId = branchId;
        this.branchName = branchName;
        this.active = active;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
    }

    public Long getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRole getRole() {
        return role;
    }

    public String getTenantId() {
        return tenantId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public boolean isSuperAdmin() {
        return this.role == UserRole.SUPER_ADMIN;
    }

    public boolean isParlourAdmin() {
        return this.role == UserRole.PARLOUR_ADMIN;
    }

    public boolean isBranchAdmin() {
        return this.role == UserRole.BRANCH_ADMIN;
    }

    public boolean isEmployee() {
        return this.role == UserRole.EMPLOYEE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}

