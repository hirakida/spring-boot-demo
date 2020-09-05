DELETE FROM `user`;
INSERT INTO `user`(name, created_at) VALUES ('user1', CURRENT_TIMESTAMP());
INSERT INTO `user`(name, created_at) VALUES ('user2', CURRENT_TIMESTAMP());
INSERT INTO `user`(name, created_at) VALUES ('user3', CURRENT_TIMESTAMP());
INSERT INTO `user`(name, created_at) VALUES ('user4', CURRENT_TIMESTAMP());
INSERT INTO `user`(name, created_at) VALUES ('user5', CURRENT_TIMESTAMP());

DELETE FROM `person`;
INSERT INTO `person`(name, created_at) VALUES ('person1', CURRENT_TIMESTAMP());
INSERT INTO `person`(name, created_at) VALUES ('person2', CURRENT_TIMESTAMP());
INSERT INTO `person`(name, created_at) VALUES ('person3', CURRENT_TIMESTAMP());
INSERT INTO `person`(name, created_at) VALUES ('person4', CURRENT_TIMESTAMP());
INSERT INTO `person`(name, created_at) VALUES ('person5', CURRENT_TIMESTAMP());

DELETE FROM `account`;
INSERT INTO `account`(name, created_at) VALUES ('account1', CURRENT_TIMESTAMP());
INSERT INTO `account`(name, created_at) VALUES ('account2', CURRENT_TIMESTAMP());
INSERT INTO `account`(name, created_at) VALUES ('account3', CURRENT_TIMESTAMP());
INSERT INTO `account`(name, created_at) VALUES ('account4', CURRENT_TIMESTAMP());
INSERT INTO `account`(name, created_at) VALUES ('account5', CURRENT_TIMESTAMP());
