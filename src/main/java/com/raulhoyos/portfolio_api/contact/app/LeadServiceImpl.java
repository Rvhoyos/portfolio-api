package com.raulhoyos.portfolio_api.contact.app;

import com.raulhoyos.portfolio_api.contact.data.Lead;
import com.raulhoyos.portfolio_api.contact.data.LeadRepository;
import com.raulhoyos.portfolio_api.contact.web.dto.LeadRequest;
import com.raulhoyos.portfolio_api.contact.web.dto.LeadResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Persists incoming leads and returns a lightweight ack.
 */
@Service
public class LeadServiceImpl implements LeadService {

    private final LeadRepository repo;

    public LeadServiceImpl(LeadRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public LeadResponse create(LeadRequest request) {
        UUID id = UUID.randomUUID();

        Lead lead = new Lead();
        lead.setId(id);
        lead.setName(request.name());
        lead.setEmail(request.email());
        lead.setOrg(request.org());
        lead.setInterestSlug(request.interestSlug());
        lead.setInterestLabel(request.interest());
        lead.setTimeline(request.timeline());
        lead.setBudget(request.budget());
        lead.setMessage(request.message());

        if (request.meta() != null) {
            lead.setMetaPath(request.meta().path());
            lead.setMetaUserAgent(request.meta().userAgent());
            lead.setUiTimestamp(request.meta().timestamp());
        }

        // sourceIp left null for now; can be populated later from HttpServletRequest if desired.
        repo.save(lead);

        return new LeadResponse(id, "created");
    }
}
