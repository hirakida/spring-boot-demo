/*
 classpath直下に以下のfileがあると自動で実行される
 - schema-(platform).sql
 - schema.sql
 - data-(platform).sql
 - data.sql
*/
CREATE TABLE IF NOT EXISTS account (
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL
);
