SELECT product_name
FROM ORDERS
WHERE customer_id = (SELECT id FROM CUSTOMERS WHERE LOWER(name) = LOWER(:name));
