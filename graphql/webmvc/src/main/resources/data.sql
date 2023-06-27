INSERT INTO user(name, role_id, created_at, updated_at) VALUES ('user1', NULL, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO user(name, role_id, created_at, updated_at) VALUES ('user2', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO user(name, role_id, created_at, updated_at) VALUES ('user3', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO user(name, role_id, created_at, updated_at) VALUES ('user4', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO user(name, role_id, created_at, updated_at) VALUES ('user5', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO role(id, name) VALUES (1, 'ROLE_USER');
INSERT INTO role(id, name) VALUES (2, 'ROLE_ADMIN');
