package com.raulhoyos.portfolio_api.contact.web;

import com.raulhoyos.portfolio_api.contact.app.LeadService;
import com.raulhoyos.portfolio_api.contact.web.dto.LeadResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
// New Spring Boot 4 package for @WebMvcTest
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * MVC slice test for the contact lead intake endpoint.
 * Verifies: POST /api/contact/lead -> 201 Created + basic response shape.
 */
@WebMvcTest(controllers = LeadController.class)
class LeadControllerTest {

    @MockitoBean
    private LeadService leadService;

    @Autowired
    private MockMvc mvc;

    @Test
    void postLead_returns201AndCreatedPayload() throws Exception {
        UUID id = UUID.fromString("11111111-2222-3333-4444-555555555555");
        Mockito.when(leadService.create(any()))
               .thenReturn(new LeadResponse(id, "created"));

        String body = """
          {
            "name": "Paul Blart",
            "email": "paul@example.com",
            "org": null,
            "interestSlug": "spa",
            "interest": null,
            "timeline": "2-4 weeks",
            "budget": "Under 5k",
            "message": "Let's talk.",
            "meta": {
              "path": "/contact",
              "userAgent": "Mozilla/5.0",
              "timestamp": "2025-11-01T17:30:00Z"
            }
          }
        """;

        mvc.perform(post("/api/contact/lead")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
           .andExpect(status().isCreated())
           .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$.id").value(id.toString()))
           .andExpect(jsonPath("$.status").value("created"));
    }

    @Test
    void postLead_missingEmail_returns400() throws Exception {
        String body = """
          {
            "name": "Raul Hoyos",
            "message": "Let's talk.",
            "meta": { "path": "/contact" }
          }
        """;

        mvc.perform(post("/api/contact/lead")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
          .andExpect(status().isBadRequest());

        Mockito.verifyNoInteractions(leadService);
    }
}