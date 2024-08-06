DELIMITER //

CREATE TRIGGER UpdateCustomerLastModified
AFTER UPDATE ON Customers
FOR EACH ROW
BEGIN
    UPDATE Customers
    SET LastModified = NOW()
    WHERE CustomerID = NEW.CustomerID;
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, CustomerID, TransactionType, Amount, TransactionDate)
    VALUES (NEW.TransactionID, NEW.CustomerID, NEW.TransactionType, NEW.Amount, NOW());
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
BEGIN
    DECLARE current_balance DECIMAL(10,2);

    IF NEW.TransactionType = 'Withdrawal' THEN
        SELECT balance INTO current_balance
        FROM Accounts
        WHERE account_id = NEW.AccountID;

        IF current_balance < NEW.Amount THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Insufficient balance for withdrawal.';
        END IF;
    ELSEIF NEW.TransactionType = 'Deposit' THEN
        IF NEW.Amount <= 0 THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Deposit amount must be positive.';
        END IF;
    END IF;
END //

DELIMITER ;
