CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_age IN NUMBER,
    p_balance IN NUMBER
) IS
    customer_exists EXCEPTION;
BEGIN

    BEGIN
        INSERT INTO customers (customer_id, name, age, balance)
        VALUES (p_customer_id, p_name, p_age, p_balance);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            RAISE customer_exists;
    END;

    COMMIT;
EXCEPTION
    WHEN customer_exists THEN
        DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_customer_id || ' already exists.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: An unexpected error occurred while adding new customer.');
END AddNewCustomer;
