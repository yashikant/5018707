DELIMITER //

CREATE FUNCTION CalculateAge(dob DATE)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE age INT;
    SET age = TIMESTAMPDIFF(YEAR, dob, CURDATE());
    RETURN age;
END //

DELIMITER ;

DELIMITER //

CREATE FUNCTION CalculateMonthlyInstallment(
    loan_amount DECIMAL(10,2),
    annual_interest_rate DECIMAL(5,2),
    duration_years INT
)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE monthly_rate DECIMAL(5,4);
    DECLARE number_of_payments INT;
    DECLARE installment DECIMAL(10,2);

    SET monthly_rate = annual_interest_rate / 100 / 12;
    SET number_of_payments = duration_years * 12;

    IF monthly_rate = 0 THEN
        SET installment = loan_amount / number_of_payments;
    ELSE
        SET installment = loan_amount * (monthly_rate / (1 - POW(1 + monthly_rate, -number_of_payments)));
    END IF;

    RETURN installment;
END //

DELIMITER ;

DELIMITER //

CREATE FUNCTION HasSufficientBalance(
    account_id INT,
    amount DECIMAL(10,2)
)
RETURNS BOOLEAN
DETERMINISTIC
BEGIN
    DECLARE current_balance DECIMAL(10,2);
    DECLARE sufficient BOOLEAN;

    SELECT balance INTO current_balance
    FROM Accounts
    WHERE account_id = account_id;

    SET sufficient = current_balance >= amount;
    RETURN sufficient;
END //

DELIMITER ;
