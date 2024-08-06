DECLARE
    CURSOR c_customers IS
        SELECT customer_id, loan_interest_rate
        FROM customers
        WHERE age > 60;
        
    v_loan_interest_rate customers.loan_interest_rate%TYPE;
BEGIN
    FOR r_customer IN c_customers LOOP
        v_loan_interest_rate := r_customer.loan_interest_rate - 0.01;
        UPDATE customers
        SET loan_interest_rate = v_loan_interest_rate
        WHERE customer_id = r_customer.customer_id;
    END LOOP;
    COMMIT;
END;
