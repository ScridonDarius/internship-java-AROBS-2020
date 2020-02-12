CREATE DATABASE shop;

CREATE TABLE user(
user_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_name VARCHAR(40) NOT NULL,
user_password VARCHAR(40) NOT NULL
);

CREATE TABLE cart(
cart_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
stock_id INT NOT NULL,
user_id INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE product(
product_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
product_name VARCHAR(50) NOT NULL,
product_price DOUBLE NOT NULL
);

CREATE TABLE cartline(
product_id INT NOT NULL,
cart_id INT NOT NULL,
cartline_quantity INT NOT NULL,
FOREIGN KEY(product_id) REFERENCES product(product_id),
FOREIGN KEY(cart_id) REFERENCES cart(cart_id)
);
CREATE TABLE stock(
stock_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
stock_quantity INT NOT NULL,
product_id INT NOT NULL,
FOREIGN KEY(product_id) REFERENCES product(product_id)
);




