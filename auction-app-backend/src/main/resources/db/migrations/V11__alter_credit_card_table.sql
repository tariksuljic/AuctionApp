ALTER TABLE credit_card
ALTER COLUMN name_on_card DROP NOT NULL,
ALTER COLUMN card_number DROP NOT NULL,
ALTER COLUMN expiration_date DROP NOT NULL;

ALTER TABLE credit_card
ADD COLUMN stripe_token VARCHAR(255);
