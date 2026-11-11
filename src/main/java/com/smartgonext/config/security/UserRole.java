package com.smartgonext.config.security;

public enum UserRole {
    SUPER_ADMIN("Super Administrator"),
    PARLOUR_ADMIN("Parlour Administrator"),
    BRANCH_ADMIN("Branch Administrator"),
    EMPLOYEE("Staff Employee");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static UserRole fromString(String roleStr) {
        if (roleStr == null) return EMPLOYEE;
        String normalized = roleStr.trim().toUpperCase().replace(" ", "_");
        if (normalized.startsWith("ROLE_")) {
            normalized = normalized.substring(5);
        }
        for (UserRole role : values()) {
            if (role.name().equalsIgnoreCase(normalized)) {
                return role;
            }
        }
        // Normalize legacy names if encountered
        if ("PARLOURADMIN".equalsIgnoreCase(normalized)) return PARLOUR_ADMIN;
        if ("BRANCHADMIN".equalsIgnoreCase(normalized)) return BRANCH_ADMIN;
        if ("RECEPTIONIST".equalsIgnoreCase(normalized)) return EMPLOYEE;
        return EMPLOYEE;
    }
}

