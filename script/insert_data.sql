INSERT INTO users(username,password,enabled)
VALUES ('ngoc','123', true);
INSERT INTO user_roles (username, role)
VALUES ('ngoc', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('ngoc', 'ROLE_USER');
INSERT INTO users(username,password,enabled)
VALUES ('aaa','123', true);
INSERT INTO user_roles (username, role)
VALUES ('aaa', 'ROLE_USER');
INSERT INTO users(username,password,enabled)
VALUES ('olivier','123', true);
INSERT INTO user_roles (username, role)
VALUES ('olivier', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('olivier', 'ROLE_USER');