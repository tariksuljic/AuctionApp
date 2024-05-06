CREATE TABLE bid (
    bid_id UUID PRIMARY KEY,
    bid_amount DECIMAL(10, 2) NOT NULL,
    bid_time TIMESTAMP NOT NULL,
    product_id UUID NOT NULL,
    user_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(product_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE notification (
    notification_id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    message_content TEXT NOT NULL,
    read_status BOOLEAN NOT NULL,
    notification_time TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
