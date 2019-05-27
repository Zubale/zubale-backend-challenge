
INSERT INTO users(username, password, enabled, name, lastname, email) VALUES ('andres','$2a$10$fDHBdfL5mEaQctpf3OXjzu3.Er57dBmaZylACM7ZpGPA4BU5up0Ki',1,'Paco','Chavez','sdsd@sd.co');
INSERT INTO users(username, password, enabled, name, lastname, email) VALUES ('admin','$2a$10$sCByUNqAt9NwtUhEMOMJ3uubM63HlcyD7/hwd2TXBfN1FPl8u3i7u',1,'Raul','Castro','sdssd@sd.co');
INSERT INTO users(username, password, enabled, name, lastname, email) VALUES ('emmanuel','$2a$10$sCByUNqAt9NwtUhEMOMJ3uubM63HlcyD7/hwd2TXBfN1FPl8u3i7u',1,'Emmanuel','Castro','sdssd@sd.com');

INSERT INTO roles(name ) VALUES ('ROLE_USER');
INSERT INTO roles(name ) VALUES ('ROLE_ADMIN');

INSERT INTO users_roles(user_id, role_id ) VALUES (1, 1);
INSERT INTO users_roles(user_id, role_id ) VALUES (2, 2);
INSERT INTO users_roles(user_id, role_id ) VALUES (2, 1);

INSERT INTO quotes(autor, book, create_at, user_id ) VALUES ('Shakespeare','Poet 1', '2018-01-01',1);
INSERT INTO quotes(autor, book, create_at, user_id ) VALUES ('Shakespeare','Poet 1', '2018-01-01',1);

INSERT INTO votes( create_at, user_id, quote_id ) VALUES ('2018-01-01',1, 1);

INSERT INTO votes( create_at, user_id, quote_id ) VALUES ('2018-01-01',2, 2);
INSERT INTO votes( create_at, user_id, quote_id ) VALUES ('2018-01-01',2, 1);
