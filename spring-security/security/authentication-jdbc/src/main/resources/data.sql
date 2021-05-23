INSERT INTO users(username, password, enabled) VALUES ('user1', '$2a$10$YpRw.2F8v342p0K0agISMOtuUgp54RhU2ZVUwBJpEBbb6IzSvaIJi', true);
INSERT INTO users(username, password, enabled) VALUES ('user2', '$2a$10$xQONjo1qTe/qeETEotypbuQ3SLGzuF0ZKE1ZWheAqG.mD0iDoD/xy', true);
INSERT INTO users(username, password, enabled) VALUES ('user3', '$2a$10$i5hB7QG4xYKdWLr17RVe3OqpJINjbfMDKk8KXZZ8x5AwqVAotG8pq', false);
INSERT INTO authorities(username, authority) VALUES ('user1', 'ROLE_USER');
INSERT INTO authorities(username, authority) VALUES ('user2', 'ROLE_USER');
INSERT INTO authorities(username, authority) VALUES ('user2', 'ROLE_ADMIN');
