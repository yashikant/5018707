-- Scenario 1: Generate Monthly Statements
DECLARE
    CURSOR c_transactions IS
        SELECT CustomerID, TransactionID, Amount, TransactionDate
        FROM Transactions
        WHERE EXTRACT(MONTH FROM TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
        AND EXTRACT(YEAR FROM TransactionDate) = EXTRACT(YEAR FROM SYSDATE);

    v_customer_id Customers.CustomerID%TYPE;
    v_transaction_id Transactions.TransactionID%TYPE;
    v_amount Transactions.Amount%TYPE;
    v_transaction_date Transactions.TransactionDate%TYPE;

BEGIN
    OPEN c_transactions;
    LOOP
        FETCH c_transactions INTO v_customer_id, v_transaction_id, v_amount, v_transaction_date;
        EXIT WHEN c_transactions%NOTFOUND;
        
        -- Print or generate the statement for each customer
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || v_customer_id || ', Transaction ID: ' || v_transaction_id || ', Amount: ' || v_amount || ', Date: ' || v_transaction_date);
    END LOOP;
    CLOSE c_transactions;
END;
/

-- Scenario 2: Apply Annual Fee
DECLARE
    CURSOR c_accounts IS
        SELECT AccountID, Balance
        FROM Accounts;

    v_account_id Accounts.AccountID%TYPE;
    v_balance Accounts.Balance%TYPE;
    v_fee DECIMAL(10,2) := 50.00; -- Annual maintenance fee

BEGIN
    OPEN c_accounts;
    LOOP
        FETCH c_accounts INTO v_account_id, v_balance;
        EXIT WHEN c_accounts%NOTFOUND;
        
        -- Deduct the annual fee from the account balance
        UPDATE Accounts
        SET Balance = v_balance - v_fee
        WHERE AccountID = v_account_id;
    END LOOP;
    CLOSE c_accounts;
END;
/

-- Scenario 3: Update Loan Interest Rates
DECLARE
    CURSOR c_loans IS
        SELECT LoanID, CurrentInterestRate
        FROM Loans;

    v_loan_id Loans.LoanID%TYPE;
    v_current_interest_rate Loans.CurrentInterestRate%TYPE;
    v_new_interest_rate DECIMAL(5,2); -- New interest rate based on policy

BEGIN
    OPEN c_loans;
    LOOP
        FETCH c_loans INTO v_loan_id, v_current_interest_rate;
        EXIT WHEN c_loans%NOTFOUND;

        -- Calculate new interest rate (example: increase by 0.5%)
        v_new_interest_rate := v_current_interest_rate + 0.50;
        
        -- Update the loan interest rate
        UPDATE Loans
        SET CurrentInterestRate = v_new_interest_rate
        WHERE LoanID = v_loan_id;
    END LOOP;
    CLOSE c_loans;
END;
/
