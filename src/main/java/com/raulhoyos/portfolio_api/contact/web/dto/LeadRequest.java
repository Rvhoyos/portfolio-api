package com.raulhoyos.portfolio_api.contact.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Instant;

/**
 * Input DTO for POST /api/contact/lead.
 * Optional fields are nullable. Validation only on requireds + sane max lengths.
 */
public record LeadRequest(
        @NotBlank @Size(max = 120) String name,
        @NotBlank @Email @Size(max = 254) String email,
        @Size(max = 160) String org,
        // Optional; case-insensitive enum check when present
        @Pattern(regexp = "(?i)^(ssg|spa|ssr|apis|devops|kubernetes)$")
        String interestSlug,
        @Size(max = 64) String interest,
        @Size(max = 120) String timeline,
        @Size(max = 120) String budget,
        @NotBlank @Size(max = 1000) String message,
        Meta meta
) {
    public record Meta(
            @Size(max = 256) String path,
            @Size(max = 512) String userAgent,
            // Optional ISO-8601; Jackson will map to Instant if present and valid
            Instant timestamp
    ) {}
}
