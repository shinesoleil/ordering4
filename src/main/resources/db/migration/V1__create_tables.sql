CREATE TABLE products (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL ,
  description VARCHAR(255) NOT NULL ,
  price DOUBLE NOT NULL
);

CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL ,
  address VARCHAR(255) NOT NULL ,
  phone VARCHAR(255) NOT NULL ,
  time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  user_id INT NOT NULL ,

  FOREIGN KEY (user_id)
    REFERENCES users(id)
);

CREATE TABLE order_items (
  order_id INT PRIMARY KEY,
  product_id INT NOT NULL ,
  quantity int NOT NULL ,

  FOREIGN KEY (order_id)
    REFERENCES orders(id),

  FOREIGN KEY (product_id)
    REFERENCES products(id)
)