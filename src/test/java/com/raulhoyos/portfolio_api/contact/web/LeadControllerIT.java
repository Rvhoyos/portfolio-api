package com.raulhoyos.portfolio_api.contact.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raulhoyos.portfolio_api.contact.data.LeadRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// New Spring Boot 4 package for @AutoConfigureMockMvc
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Full-context integration test:
 * - Boots the whole app so Flyway runs and real MVC wiring is used.
 * - Posts JSON to the controller and asserts a DB row exists.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class LeadControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private LeadRepository repo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void postLead_persistsRowAndReturns201() throws Exception {
        String body = """
          {
            "name": "Paul Blart",
            "email": "Paul@example.com",
            "message": "End-to-end integration test",
            "interestSlug": "spa",
            "meta": { "path": "/contact" }
          }
        """;

        // POST and capture raw JSON response
        String json = mvc.perform(post("/api/contact/lead")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Extract UUID from response: {"id":"...","status":"created"}
        JsonNode node = objectMapper.readTree(json);
        UUID id = UUID.fromString(node.get("id").asText());

        // Assert row exists
        assertThat(repo.findById(id)).isPresent();

        // Cleanup
        repo.deleteById(id);
    }
}