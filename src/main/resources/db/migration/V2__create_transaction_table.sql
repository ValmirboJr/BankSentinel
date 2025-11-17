CREATE TABLE transaction (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
bank_account_id BIGINT NOT NULL,
event_time TIMESTAMP NOT NULL,
amount DECIMAL(15,2) NOT NULL,
payment_method VARCHAR(20),
description VARCHAR(255) NOT NULL,
mcc INT,
category VARCHAR(60),
CONSTRAINT fk_txn_account FOREIGN KEY (bank_account_id)REFERENCES bank_account(id) ON DELETE CASCADE,
INDEX idx_transaction_account_event (bank_account_id, event_time),
INDEX idx_transaction_category (category)
);