-- DROP TABLE IF EXISTS account;
CREATE TABLE IF NOT EXISTS account (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `email` VARCHAR(30) NOT NULL
);