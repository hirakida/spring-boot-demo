/*
 If the following files exist in classpath, they are executed automatically.
 - schema-(platform).sql
 - schema.sql
 - data-(platform).sql
 - data.sql
*/
CREATE TABLE IF NOT EXISTS `user`
(
    `id`         INT PRIMARY KEY AUTO_INCREMENT,
    `name`       VARCHAR(30) NOT NULL,
    `created_at` DATETIME    NOT NULL,
    `updated_at` DATETIME    NOT NULL
);

CREATE TABLE IF NOT EXISTS person
(
    `id`         INT PRIMARY KEY AUTO_INCREMENT,
    `name`       VARCHAR(30) NOT NULL,
    `created_at` DATETIME    NOT NULL,
    `updated_at` DATETIME    NOT NULL
);
