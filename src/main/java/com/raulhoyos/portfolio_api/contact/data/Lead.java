package com.raulhoyos.portfolio_api.contact.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

/**
 * JPA entity mapped to the "leads" table.
 * Note:
 * - ID is a UUID set by the service (no DB/default generator).
 * - received_at uses the DB default; we mark it insertable=false so Hibernate omits it on INSERT.
 * - source_ip is stored as text here for portability (Postgres column is INET).
 */
@Entity
@Table(name = "leads")
public class Lead {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @Column(name = "email", nullable = false, length = 254)
    private String email;

    @Column(name = "org", length = 160)
    private String org;

    @Column(name = "interest_slug", length = 16)
    private String interestSlug;

    @Column(name = "interest_label", length = 64)
    private String interestLabel;

    @Column(name = "timeline", length = 120)
    private String timeline;

    @Column(name = "budget", length = 120)
    private String budget;

    @Column(name = "message", nullable = false, length = 1000)
    private String message;

    @Column(name = "meta_path", length = 256)
    private String metaPath;

    @Column(name = "meta_user_agent", length = 512)
    private String metaUserAgent;

    @Column(name = "ui_timestamp")
    private Instant uiTimestamp;

    @Column(name = "received_at", insertable = false, updatable = false)
    private Instant receivedAt;

    // Stored as text even though DB column is INET; convert in service if needed.
    @Column(name = "source_ip", length = 64)
    private String sourceIp;

    /** JPA requires a no-arg constructor. */
    public Lead() {}

    // Getters & setters

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getOrg() { return org; }
    public void setOrg(String org) { this.org = org; }

    public String getInterestSlug() { return interestSlug; }
    public void setInterestSlug(String interestSlug) { this.interestSlug = interestSlug; }

    public String getInterestLabel() { return interestLabel; }
    public void setInterestLabel(String interestLabel) { this.interestLabel = interestLabel; }

    public String getTimeline() { return timeline; }
    public void setTimeline(String timeline) { this.timeline = timeline; }

    public String getBudget() { return budget; }
    public void setBudget(String budget) { this.budget = budget; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getMetaPath() { return metaPath; }
    public void setMetaPath(String metaPath) { this.metaPath = metaPath; }

    public String getMetaUserAgent() { return metaUserAgent; }
    public void setMetaUserAgent(String metaUserAgent) { this.metaUserAgent = metaUserAgent; }

    public Instant getUiTimestamp() { return uiTimestamp; }
    public void setUiTimestamp(Instant uiTimestamp) { this.uiTimestamp = uiTimestamp; }

    public Instant getReceivedAt() { return receivedAt; }
    public void setReceivedAt(Instant receivedAt) { this.receivedAt = receivedAt; }

    public String getSourceIp() { return sourceIp; }
    public void setSourceIp(String sourceIp) { this.sourceIp = sourceIp; }
}
