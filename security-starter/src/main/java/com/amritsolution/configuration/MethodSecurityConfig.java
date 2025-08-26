package com.amritsolution.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) // Enables @PreAuthorize and @PostAuthorize
public class MethodSecurityConfig {
    // You can also register custom PermissionEvaluator here if needed
}