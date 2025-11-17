CREATE TABLE bank_account (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
account_number VARCHAR(255) NOT NULL UNIQUE,
banks VARCHAR(50),
type_account VARCHAR(50),
currency VARCHAR(10),
account_balance DECIMAL(19, 2),
registration_date DATETIME,
UNIQUE KEY uk_account_number(account_number)
);