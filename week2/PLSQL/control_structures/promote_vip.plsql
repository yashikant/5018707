DECLARE
    CURSOR c_customers IS
        SELECT customer_id, balance
        FROM customers;
        
BEGIN
    FOR r_customer IN c_customers LOOP
        IF r_customer.balance > 10000 THEN
            UPDATE customers
            SET is_vip = TRUE
            WHERE customer_id = r_customer.customer_id;
        END IF;
    END LOOP;
    COMMIT;
END;
