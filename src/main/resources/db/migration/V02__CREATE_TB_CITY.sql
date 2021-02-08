CREATE TABLE category (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	version INT(20) NOT NULL,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE product (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	version INT(20) NOT NULL,
	name VARCHAR(50) NOT NULL,
	price DECIMAL(10,2) NOT NULL,
	quantity_stock INT(20) NOT NULL,
	category_code BIGINT(20) NOT NULL,
	FOREIGN KEY (category_code) REFERENCES category(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE payment (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	version INT(20) NOT NULL,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cart (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	version INT(20) NOT NULL,
	total_price DECIMAL(10,2),
	date DATETIME NOT NULL,
	user_db_code BIGINT(20) NOT NULL,
	payment_code BIGINT(20),
	FOREIGN KEY (user_db_code) REFERENCES user_db(code),
	FOREIGN KEY (payment_code) REFERENCES payment(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE shipping (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	version INT(20) NOT NULL,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cart_shipping (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	version INT(20) NOT NULL,
	total_price DECIMAL(10,2) NOT NULL,
	delivery_time VARCHAR(50) NOT NULL,
	shipping_code BIGINT(20) NOT NULL,
	cart_code BIGINT(20) NOT NULL,
	FOREIGN KEY (cart_code) REFERENCES cart(code),
	FOREIGN KEY (shipping_code) REFERENCES shipping(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cart_product (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	version INT(20) NOT NULL,
	quantity INT(20) NOT NULL,
	cart_code BIGINT(20) NOT NULL,
	product_code BIGINT(20) NOT NULL,
	FOREIGN KEY (cart_code) REFERENCES cart(code),
	FOREIGN KEY (product_code) REFERENCES product(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO category (code, version, name) values (1, 0, 'Alimentos');
INSERT INTO category (code, version, name) values (2, 0, 'Bebidas');
INSERT INTO category (code, version, name) values (3, 0, 'Higiene');
INSERT INTO category (code, version, name) values (4, 0, 'Limpeza');

INSERT INTO shipping (code, version, name) values (1, 0, 'Correios - PAC');
INSERT INTO shipping (code, version, name) values (2, 0, 'Correios - SEDEX');
INSERT INTO shipping (code, version, name) values (3, 0, 'JADLOG');

INSERT INTO product (code, version, name, price, quantity_stock, category_code) values (1, 0, 'Detergente Líquido', '9.10', 20, 4);
INSERT INTO product (code, version, name, price, quantity_stock, category_code) values (2, 0, 'Shampoo', '10.20', 20, 3);
INSERT INTO product (code, version, name, price, quantity_stock, category_code) values (3, 0, 'Leite em Pó', '11.30', 20, 2);
INSERT INTO product (code, version, name, price, quantity_stock, category_code) values (4, 0, 'Margarina', '12.40', 20, 1);

INSERT INTO payment (code, version, name) values (1, 0, 'Boleto Bancário');
INSERT INTO payment (code, version, name) values (2, 0, 'Cartão de Débito');
INSERT INTO payment (code, version, name) values (3, 0, 'Cartão de Crédito');

