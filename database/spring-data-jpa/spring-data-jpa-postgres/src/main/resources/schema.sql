CREATE TABLE IF NOT EXISTS "account" (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR(30) NOT NULL,
  "enabled" BOOLEAN NOT NULL DEFAULT TRUE,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp NOT NULL
);
