-------------------------------------------------------
--------- SET UP SCHEMA FOR IM-MEMORY DB - H2 ---------
-------------------------------------------------------

------------------------------------- DEFAULT SPRING SECURITY DB SCHEMA -------------------------------------
-- create users table --
--CREATE TABLE users (
--    username VARCHAR(50) NOT NULL PRIMARY KEY,
--    password VARCHAR(100) NOT NULL,
--    enabled  BOOLEAN     NOT NULL
--);
--
---- create authorities/role table --
--CREATE TABLE authorities (
--    username  VARCHAR(50) NOT NULL,
--    authority VARCHAR(50) NOT NULL,
--    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
--);
--
---- username + authority -> unique key of authorities table --
--CREATE UNIQUE INDEX uk_username_authority ON authorities(username, authority);


------------------------------------- CUSTOM SPRING SECURITY DB SCHEMA -------------------------------------
-- create members table --
CREATE TABLE members (
    user_id VARCHAR(50) NOT NULL PRIMARY KEY,
    pw      VARCHAR(100) NOT NULL,
    active  BOOLEAN     NOT NULL
);

-- create roles table --
CREATE TABLE roles (
    user_id VARCHAR(50) NOT NULL,
    role    VARCHAR(50) NOT NULL,
    CONSTRAINT fk_roles_members FOREIGN KEY (user_id) REFERENCES members(user_id)
);

-- user_id + role -> unique key of roles table --
CREATE UNIQUE INDEX uk_user_id_role ON roles(user_id, role);