/*
 - schema-(platform).sql
 - schema.sql
 - data-(platform).sql
 - data.sql
*/
DELETE FROM `person`;
INSERT INTO `person`(name, enabled, created_at, updated_at) VALUES ('person1', true, '2019-01-02T12:00:00', '2019-01-02T12:00:00');
INSERT INTO `person`(name, enabled, created_at, updated_at) VALUES ('person2', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO `person`(name, enabled, created_at, updated_at) VALUES ('person3', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO `person`(name, enabled, created_at, updated_at) VALUES ('person4', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO `person`(name, enabled, created_at, updated_at) VALUES ('person5', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO `person`(name, enabled, created_at, updated_at) VALUES ('person6', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
