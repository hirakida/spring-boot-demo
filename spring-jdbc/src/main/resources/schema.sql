/*
 If the following files exist in classpath, they are executed automatically.
 - schema-(platform).sql
 - schema.sql
 - data-(platform).sql
 - data.sql
*/
CREATE TABLE IF NOT EXISTS account (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL
);
