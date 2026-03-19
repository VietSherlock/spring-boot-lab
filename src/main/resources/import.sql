------- data.sql -------
-- The data.sql is executed before Hibernate initializes and creates tables
-- Use spring.jpa.defer-datasource-initialization=true to run data.sql AFTER Hibernate creates the schema

------- import.sql -------
-- The import.sql is executed after Hibernate initializes and creates tables
-- not support multi-row insert

-- employee --
INSERT INTO employee (id, first_name, last_name, email) VALUES (1, 'Cristiano', 'Ronaldo', 'cristiano@example.com');
INSERT INTO employee (id, first_name, last_name, email) VALUES (2, 'Marcus', 'Aurelius', 'marcus@example.com');
INSERT INTO employee (id, first_name, last_name, email) VALUES (3, 'Viet', 'Le Nguyen Ngoc', 'vietlnn@example.com');