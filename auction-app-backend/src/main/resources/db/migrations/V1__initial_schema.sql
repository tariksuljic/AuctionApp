CREATE TABLE category (
    category_id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parent_category_id UUID,
    FOREIGN KEY (parent_category_id) REFERENCES category(category_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE product (
    product_id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    start_price DECIMAL(10, 2) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    category_id UUID NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(category_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
