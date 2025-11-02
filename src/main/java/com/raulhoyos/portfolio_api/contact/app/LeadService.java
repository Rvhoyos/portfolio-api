package com.raulhoyos.portfolio_api.contact.app;

import com.raulhoyos.portfolio_api.contact.web.dto.LeadRequest;
import com.raulhoyos.portfolio_api.contact.web.dto.LeadResponse;

/**
 * Temporary stub for the service boundary.
 * Now accepts the typed LeadRequest so the controller can pass validated input.
 */
public interface LeadService {
    LeadResponse create(LeadRequest request);
}
