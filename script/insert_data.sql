INSERT INTO users(username,password,enabled)
VALUES ('ngoc','sennheiser', true);
INSERT INTO user_roles (username, role)
VALUES ('ngoc', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('ngoc', 'ROLE_USER');
INSERT INTO users(username,password,enabled)
VALUES ('aaa','123', true);
INSERT INTO user_roles (username, role)
VALUES ('aaa', 'ROLE_USER');
INSERT INTO users(username,password,enabled)
VALUES ('alexson','sony', true);
INSERT INTO user_roles (username, role)
VALUES ('alexson', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('alexson', 'ROLE_USER');