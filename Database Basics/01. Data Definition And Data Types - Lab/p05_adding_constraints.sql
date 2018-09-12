ALTER TABLE products
ADD CONSTRAINT fk_products_categories_id 
FOREIGN KEY (category_id) 
REFERENCES categories(id);