CREATE TABLE IF NOT EXISTS "person" (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR(30) NOT NULL,
  "created_at" timestamp NOT NULL,
  "updated_at" timestamp NOT NULL
);
