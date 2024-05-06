ALTER TABLE notification
DROP COLUMN message_content;

ALTER TABLE notification
ADD COLUMN notification_type VARCHAR(50) NOT NULL;
