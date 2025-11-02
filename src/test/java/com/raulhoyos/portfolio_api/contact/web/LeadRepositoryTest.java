package com.raulhoyos.portfolio_api.contact.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test for LeadRepository using the dev profile:
 * - Boots full Spring context so Flyway runs against the dev DB.
 * - @Transactional ensures the insert is rolled back after the test.
 */
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
class LeadRepositoryTest {

    @Autowired
    private LeadRepository repo;

    @jakarta.persistence.PersistenceContext
    private jakarta.persistence.EntityManager em;

    @Test
    void save_and_findById_roundTrips() {
        Lead lead = new Lead();
        UUID id = UUID.randomUUID();
        lead.setId(id);
        lead.setName("Test User");
        lead.setEmail("test@example.com");
        lead.setMessage("Hello from test");
        lead.setMetaPath("/contact");
        lead.setUiTimestamp(Instant.parse("2025-01-01T00:00:00Z"));

        repo.saveAndFlush(lead);

        // Ensure we read from DB, not first-level cache
        em.clear();

        Optional<Lead> loaded = repo.findById(id);
        assertThat(loaded).isPresent();
        assertThat(loaded.get().getReceivedAt()).isNotNull(); // now set by DB default
    }
}

