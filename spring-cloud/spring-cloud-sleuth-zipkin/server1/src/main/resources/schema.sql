CREATE TABLE IF NOT EXISTS `user` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `enabled` INT(1) NOT NULL default true,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL
);
