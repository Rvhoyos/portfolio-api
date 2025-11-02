package com.raulhoyos.portfolio_api.contact.web.dto;

import java.util.UUID;

/** Outbound payload to the UI. */
public record LeadResponse(UUID id, String status) {}
