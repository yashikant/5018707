DECLARE
    CURSOR c_loans IS
        SELECT customer_id, loan_due_date
        FROM loans
        WHERE loan_due_date BETWEEN SYSDATE AND SYSDATE + 30;
        
BEGIN
    FOR r_loan IN c_loans LOOP
       
        DBMS_OUTPUT.PUT_LINE('Reminder: Customer ' || r_loan.customer_id || ' has a loan due on ' || r_loan.loan_due_date);
    END LOOP;
END;
