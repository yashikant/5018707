
DELIMITER //

CREATE PROCEDURE ProcessMonthlyInterest()
BEGIN
    UPDATE SavingsAccounts
    SET balance = balance * 1.01;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE UpdateEmployeeBonus(IN dept_id INT, IN bonus_percentage DECIMAL(5,2))
BEGIN
    UPDATE Employees
    SET salary = salary * (1 + bonus_percentage / 100)
    WHERE department_id = dept_id;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE TransferFunds(
    IN from_account_id INT,
    IN to_account_id INT,
    IN transfer_amount DECIMAL(10,2)
)
BEGIN
    DECLARE source_balance DECIMAL(10,2);

    SELECT balance INTO source_balance
    FROM Accounts
    WHERE account_id = from_account_id;

    IF source_balance >= transfer_amount THEN
        UPDATE Accounts
        SET balance = balance - transfer_amount
        WHERE account_id = from_account_id;

        UPDATE Accounts
        SET balance = balance + transfer_amount
        WHERE account_id = to_account_id;
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Insufficient funds in the source account.';
    END IF;
END //

DELIMITER ;
