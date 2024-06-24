CREATE TABLE CUSTOMERS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    surname VARCHAR(50),
    age INT,
    phone_number VARCHAR(15)
);

CREATE TABLE ORDERS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE,
    customer_id INT,
    product_name VARCHAR(100),
    amount DECIMAL(10, 2),
    FOREIGN KEY (customer_id) REFERENCES CUSTOMERS(id)
);
