package com.raulhoyos.portfolio_api.contact.web;

import com.raulhoyos.portfolio_api.contact.app.LeadService;
import com.raulhoyos.portfolio_api.contact.web.dto.LeadRequest;
import com.raulhoyos.portfolio_api.contact.web.dto.LeadResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Handles lead intake from the portfolio UI.
 *
 * Now accepts a typed {@link LeadRequest} so Bean Validation can run at the edge.
 * Service still takes Object for now; passing LeadRequest satisfies that contract.
 */
@RestController
@RequestMapping(path = "/api/contact")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping(
        path = "/lead",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LeadResponse> createLead(@RequestBody @Valid LeadRequest request) {
        LeadResponse response = leadService.create(request); // LeadRequest is an Object; OK for now.
        URI location = URI.create("/api/contact/lead/" + response.id());
        return ResponseEntity.created(location).body(response);
    }
}
