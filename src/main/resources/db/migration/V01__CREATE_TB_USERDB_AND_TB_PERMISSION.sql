CREATE TABLE user_db (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	version INT(20) NOT NULL,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE address (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	version INT(20) NOT NULL,
	zip_code VARCHAR(50) NOT NULL,
	street VARCHAR(50) NOT NULL,
	city VARCHAR(50) NOT NULL,
	country VARCHAR(50) NOT NULL,
	code_user_db BIGINT(20) NOT NULL,
	FOREIGN KEY (code_user_db) REFERENCES user_db(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permission (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	version INT(20) NOT NULL,
	description VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_db_permission (
	code_user_db BIGINT(20) NOT NULL,
	code_permission BIGINT(20) NOT NULL,
	PRIMARY KEY (code_user_db, code_permission),
	FOREIGN KEY (code_user_db) REFERENCES user_db(code),
	FOREIGN KEY (code_permission) REFERENCES permission(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user_db (code, version, name, email, password) 
values (1, 0, 'João', 'joao@gmail.com', '$2a$10$XkFZ/d3LJsR9Tq2vgX74U.IdKr2bo0ooBbFWpyCz0fik4XjAXblMC');

INSERT INTO address (code, version, zip_code, street, city, country, code_user_db) 
values (1, 0, '65075-798', 'Rua das Andorinhas', 'São Luís/MA', 'Brasil', 1);

INSERT INTO address (code, version, zip_code, street, city, country, code_user_db) 
values (2, 0, '65075-546', 'Rua do Sol', 'São Luís/MA', 'Brasil', 1);

INSERT INTO user_db (code, version, name, email, password) 
values (2, 0, 'Maria', 'maria@gmail.com', '$2a$10$XkFZ/d3LJsR9Tq2vgX74U.IdKr2bo0ooBbFWpyCz0fik4XjAXblMC');

INSERT INTO address (code, version, zip_code, street, city, country, code_user_db) 
values (3, 0, '65075-798', 'Rua das Gaivotas', 'Balsas/MA', 'Brasil', 2);

INSERT INTO permission (code, version, description) values (1, 0, 'ROLE_PRODUCT');
INSERT INTO permission (code, version, description) values (2, 0, 'ROLE_USER');
INSERT INTO permission (code, version, description) values (3, 0, 'ROLE_CART');

-- JOAO
INSERT INTO user_db_permission (code_user_db, code_permission) values (1, 1);
INSERT INTO user_db_permission (code_user_db, code_permission) values (1, 2);
INSERT INTO user_db_permission (code_user_db, code_permission) values (1, 3);

-- MARIA
INSERT INTO user_db_permission (code_user_db, code_permission) values (2, 2);
INSERT INTO user_db_permission (code_user_db, code_permission) values (2, 3);