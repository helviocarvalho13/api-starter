CREATE TABLE user_db (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	version INT(20) NOT NULL,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL
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

INSERT INTO user_db (code, version, name, email, password) 
values (2, 0, 'Maria', 'maria@gmail.com', '$2a$10$XkFZ/d3LJsR9Tq2vgX74U.IdKr2bo0ooBbFWpyCz0fik4XjAXblMC');

INSERT INTO permission (code, version, description) values (1, 0, 'ROLE_PLAYLIST');

-- admin
INSERT INTO user_db_permission (code_user_db, code_permission) values (1, 1);

-- maria
