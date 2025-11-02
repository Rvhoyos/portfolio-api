package com.raulhoyos.portfolio_api.contact.app;

import com.raulhoyos.portfolio_api.contact.web.dto.LeadRequest;
import com.raulhoyos.portfolio_api.contact.web.dto.LeadResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LeadServiceImpl implements LeadService {
    @Override
    public LeadResponse create(LeadRequest request) {
        // Stub implementation MVC test will override via mock.
        return new LeadResponse(UUID.randomUUID(), "created");
    }
}
