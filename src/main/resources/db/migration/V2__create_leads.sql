-- Lead intake storage (PostgreSQL)
-- Matches POST /api/contact/lead request fields. Minimal columns for v1.

CREATE TABLE IF NOT EXISTS leads (
  id               UUID PRIMARY KEY,
  name             VARCHAR(120)  NOT NULL,
  email            VARCHAR(254)  NOT NULL,
  org              VARCHAR(160),
  interest_slug    VARCHAR(16)   CHECK (interest_slug IN ('ssg','spa','ssr','apis','devops','kubernetes')),
  interest_label   VARCHAR(64),
  timeline         VARCHAR(120),
  budget           VARCHAR(120),
  message          VARCHAR(1000) NOT NULL,
  meta_path        VARCHAR(256),
  meta_user_agent  VARCHAR(512),
  ui_timestamp     TIMESTAMPTZ,
  received_at      TIMESTAMPTZ   NOT NULL DEFAULT now(),
  source_ip        INET
);

-- Helpful lookups (optional but cheap)
CREATE INDEX IF NOT EXISTS idx_leads_received_at ON leads (received_at);
CREATE INDEX IF NOT EXISTS idx_leads_email ON leads (email);
