-- Align source_ip with JPA String mapping
ALTER TABLE leads
  ALTER COLUMN source_ip TYPE VARCHAR(64)
  USING source_ip::text;
