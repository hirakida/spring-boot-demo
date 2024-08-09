CREATE TABLE IF NOT EXISTS "user" (
  id SERIAL PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  created_at timestamp NOT NULL,
  updated_at timestamp NOT NULL
);
