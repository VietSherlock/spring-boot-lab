-------------------------------------------------------
-------------------- POPULATE DATA --------------------
-------------------------------------------------------

------- data.sql -------
-- The data.sql is executed before Hibernate initializes and creates tables
-- Use spring.jpa.defer-datasource-initialization=true to run data.sql AFTER Hibernate creates the schema

------- import.sql -------
-- The import.sql is executed after Hibernate initializes and creates tables
-- not support multi-row insert

-- employee --
insert into employee (first_name, last_name, email) values ('Cristiano', 'Ronaldo', 'cristiano@example.com');
insert into employee (first_name, last_name, email) values ('Marcus', 'Aurelius', 'marcus@example.com');
insert into employee (first_name, last_name, email) values ('Viet', 'Le Nguyen Ngoc', 'vietlnn@example.com');

-- product --
insert into product (product_no, product_name, price) values ('product_no1', 'Iphone', 1000);
insert into product (product_no, product_name, price) values ('product_no2', 'Macbook', 2000);

---- users --
----INSERT INTO users (username, password, enabled) VALUES ('john', '{noop}test123', true);
--INSERT INTO users (username, password, enabled) VALUES ('john',  '{bcrypt}$2a$10$bFyR2HSvVy0nyecOG3gPdui8sLJBr9nin5vsEqyeQaB7g7e5yoncy', true);
--INSERT INTO users (username, password, enabled) VALUES ('mary',  '{bcrypt}$2a$10$HunuysXrMoG0QEyCCTF1Bulh/PfAblhFlm2WFZLjDi7joDucx/HPm', true);
--INSERT INTO users (username, password, enabled) VALUES ('susan', '{bcrypt}$2a$10$WdQv1JiIlNUwL5IDHNzBCewSNUhoCcacVduxbus/DhHlObcyUzqAu', true);
--
--
---- authorities --
--INSERT INTO authorities (username, authority) VALUES ('john', 'ROLE_EMPLOYEE');
--INSERT INTO authorities (username, authority) VALUES ('mary', 'ROLE_EMPLOYEE');
--INSERT INTO authorities (username, authority) VALUES ('mary', 'ROLE_MANAGER');
--INSERT INTO authorities (username, authority) VALUES ('susan', 'ROLE_EMPLOYEE');
--INSERT INTO authorities (username, authority) VALUES ('susan', 'ROLE_MANAGER');
--INSERT INTO authorities (username, authority) VALUES ('susan', 'ROLE_ADMIN');

-- members --
--INSERT INTO members (user_id, pw, active) VALUES ('john', '{noop}test123', true);
insert into members (user_id, pw, active) values ('john',  '{bcrypt}$2a$10$bFyR2HSvVy0nyecOG3gPdui8sLJBr9nin5vsEqyeQaB7g7e5yoncy', true);
insert into members (user_id, pw, active) values ('mary',  '{bcrypt}$2a$10$HunuysXrMoG0QEyCCTF1Bulh/PfAblhFlm2WFZLjDi7joDucx/HPm', true);
insert into members (user_id, pw, active) values ('susan', '{bcrypt}$2a$10$WdQv1JiIlNUwL5IDHNzBCewSNUhoCcacVduxbus/DhHlObcyUzqAu', true);


-- roles --
insert into roles (user_id, role) values ('john', 'ROLE_EMPLOYEE');
insert into roles (user_id, role) values ('mary', 'ROLE_EMPLOYEE');
insert into roles (user_id, role) values ('mary', 'ROLE_MANAGER');
insert into roles (user_id, role) values ('susan', 'ROLE_EMPLOYEE');
insert into roles (user_id, role) values ('susan', 'ROLE_MANAGER');
insert into roles (user_id, role) values ('susan', 'ROLE_ADMIN');