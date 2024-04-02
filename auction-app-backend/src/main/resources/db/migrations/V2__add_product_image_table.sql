CREATE TABLE product_image (
   image_id UUID PRIMARY KEY,
   product_id UUID NOT NULL,
   image_url VARCHAR(255) NOT NULL,
   FOREIGN KEY (product_id) REFERENCES product(product_id)
       ON UPDATE CASCADE
       ON DELETE CASCADE
);
