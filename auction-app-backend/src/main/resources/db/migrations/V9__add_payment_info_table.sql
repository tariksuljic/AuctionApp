CREATE TABLE payment_info (
    payment_info_id UUID PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    zip_code VARCHAR(45) NOT NULL,
    name_on_card VARCHAR(255) NOT NULL,
    card_number VARCHAR(255) NOT NULL,
    expiration_date DATE NOT NULL
);

ALTER TABLE users
ADD COLUMN payment_info_id UUID;

ALTER TABLE users
ADD FOREIGN KEY (payment_info_id) REFERENCES payment_info(payment_info_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE;

ALTER TABLE product
ADD COLUMN payment_info_id UUID;

ALTER TABLE product
ADD FOREIGN KEY (payment_info_id) REFERENCES payment_info(payment_info_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE;
