ALTER TABLE bank_account
    ADD COLUMN customer_phone VARCHAR(20);

UPDATE bank_account
SET customer_phone = '+5511999999999'
WHERE customer_phone IS NULL;