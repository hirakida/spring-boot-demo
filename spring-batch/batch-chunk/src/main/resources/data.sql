DELETE FROM `user`;
DELETE FROM `member`;
INSERT INTO `user`(`name`, `enabled`, `created_at`, `updated_at`) VALUES ('user1', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO `user`(`name`, `enabled`, `created_at`, `updated_at`) VALUES ('user2', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO `user`(`name`, `enabled`, `created_at`, `updated_at`) VALUES ('user3', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO `user`(`name`, `enabled`, `created_at`, `updated_at`) VALUES ('user4', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO `user`(`name`, `enabled`, `created_at`, `updated_at`) VALUES ('user5', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
