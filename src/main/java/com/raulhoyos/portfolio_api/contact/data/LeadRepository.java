package com.raulhoyos.portfolio_api.contact.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Spring Data JPA repository for Lead entities.
 * No custom methods yet—basic save/find/delete are inherited.
 */
public interface LeadRepository extends JpaRepository<Lead, UUID> {}
