DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `age` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL
);