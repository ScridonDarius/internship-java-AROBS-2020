CREATE DATABASE shopApplication;

CREATE TABLE user(
user_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_name VARCHAR(40) UNIQUE NOT NULL,
user_password VARCHAR(40) NOT NULL
);

CREATE TABLE cart(
cart_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
user_id INT UNIQUE NOT NULL,
FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE cartline(
product_id INT NOT NULL,
cart_id INT NOT NULL,
cartline_quantity INT NOT NULL,
FOREIGN KEY(product_id) REFERENCES product(product_id),
FOREIGN KEY(cart_id) REFERENCES cart(cart_id)
);

CREATE TABLE product(
product_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
product_name VARCHAR(50) NOT NULL,
product_price DOUBLE NOT NULL,
product_quantity INT NOT NULL
);




