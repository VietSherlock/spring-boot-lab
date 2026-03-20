------- data.sql -------
-- The data.sql is executed before Hibernate initializes and creates tables
-- Use spring.jpa.defer-datasource-initialization=true to run data.sql AFTER Hibernate creates the schema

------- import.sql -------
-- The import.sql is executed after Hibernate initializes and creates tables
-- not support multi-row insert

-- employee --
INSERT INTO employee (first_name, last_name, email) VALUES ('Cristiano', 'Ronaldo', 'cristiano@example.com');
INSERT INTO employee (first_name, last_name, email) VALUES ('Marcus', 'Aurelius', 'marcus@example.com');
INSERT INTO employee (first_name, last_name, email) VALUES ('Viet', 'Le Nguyen Ngoc', 'vietlnn@example.com');

-- product --
INSERT INTO product (product_no, product_name, price) VALUES ('product_no1', 'Iphone', 1000);
INSERT INTO product (product_no, product_name, price) VALUES ('product_no2', 'Macbook', 2000);