INSERT INTO user(id, name, role_id, created_at, updated_at) VALUES (1, 'user1', NULL, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO user(id, name, role_id, created_at, updated_at) VALUES (2, 'user2', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO user(id, name, role_id, created_at, updated_at) VALUES (3, 'user3', 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO user(id, name, role_id, created_at, updated_at) VALUES (4, 'user4', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
INSERT INTO user(id, name, role_id, created_at, updated_at) VALUES (5, 'user5', 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO role(id, name) VALUES (1, 'ROLE_USER');
INSERT INTO role(id, name) VALUES (2, 'ROLE_ADMIN');
