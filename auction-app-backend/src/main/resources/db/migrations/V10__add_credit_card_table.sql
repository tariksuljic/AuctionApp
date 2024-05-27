CREATE TABLE credit_card (
    credit_card_id UUID PRIMARY KEY,
    name_on_card VARCHAR(255) NOT NULL,
    card_number VARCHAR(255) NOT NULL,
    expiration_date DATE NOT NULL
);

ALTER TABLE payment_info
ADD COLUMN credit_card_id UUID;

ALTER TABLE payment_info
    ADD FOREIGN KEY (credit_card_id) REFERENCES credit_card(credit_card_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE;

ALTER TABLE payment_info
DROP COLUMN name_on_card,
DROP COLUMN card_number,
DROP COLUMN expiration_date;
