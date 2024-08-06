DELIMITER //

CREATE PROCEDURE ProcessMonthlyInterest()
BEGIN
    UPDATE SavingsAccounts
    SET balance = balance * 1.01;
END //

DELIMITER ;
