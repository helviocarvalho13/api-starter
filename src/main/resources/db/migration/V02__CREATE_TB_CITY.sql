CREATE TABLE city (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	version INT(20) NOT NULL,
	city VARCHAR(50) NOT NULL,
	country VARCHAR(50) NOT NULL,
	id VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;