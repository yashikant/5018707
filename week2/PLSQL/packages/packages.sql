-- Package for Customer Management
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(p_name VARCHAR2, p_dob DATE);
    PROCEDURE UpdateCustomer(p_customer_id INT, p_name VARCHAR2, p_dob DATE);
    FUNCTION GetCustomerBalance(p_customer_id INT) RETURN DECIMAL(10,2);
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(p_name VARCHAR2, p_dob DATE) IS
    BEGIN
        INSERT INTO Customers (Name, DateOfBirth)
        VALUES (p_name, p_dob);
    END AddCustomer;

    PROCEDURE UpdateCustomer(p_customer_id INT, p_name VARCHAR2, p_dob DATE) IS
    BEGIN
        UPDATE Customers
        SET Name = p_name, DateOfBirth = p_dob
        WHERE CustomerID = p_customer_id;
    END UpdateCustomer;

    FUNCTION GetCustomerBalance(p_customer_id INT) RETURN DECIMAL(10,2) IS
        v_balance DECIMAL(10,2);
    BEGIN
        SELECT SUM(balances) INTO v_balance
        FROM Accounts
        WHERE CustomerID = p_customer_id;
        
        RETURN v_balance;
    END GetCustomerBalance;

END CustomerManagement;
/

-- Package for Employee Management
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(p_name VARCHAR2, p_salary DECIMAL(10,2));
    PROCEDURE UpdateEmployee(p_employee_id INT, p_name VARCHAR2, p_salary DECIMAL(10,2));
    FUNCTION CalculateAnnualSalary(p_employee_id INT) RETURN DECIMAL(10,2);
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(p_name VARCHAR2, p_salary DECIMAL(10,2)) IS
    BEGIN
        INSERT INTO Employees (Name, Salary)
        VALUES (p_name, p_salary);
    END HireEmployee;

    PROCEDURE UpdateEmployee(p_employee_id INT, p_name VARCHAR2, p_salary DECIMAL(10,2)) IS
    BEGIN
        UPDATE Employees
        SET Name = p_name, Salary = p_salary
        WHERE EmployeeID = p_employee_id;
    END UpdateEmployee;

    FUNCTION CalculateAnnualSalary(p_employee_id INT) RETURN DECIMAL(10,2) IS
        v_annual_salary DECIMAL(10,2);
    BEGIN
        SELECT Salary * 12 INTO v_annual_salary
        FROM Employees
        WHERE EmployeeID = p_employee_id;
        
        RETURN v_annual_salary;
    END CalculateAnnualSalary;

END EmployeeManagement;
/

-- Package for Account Operations
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(p_customer_id INT, p_initial_balance DECIMAL(10,2));
    PROCEDURE CloseAccount(p_account_id INT);
    FUNCTION GetTotalBalance(p_customer_id INT) RETURN DECIMAL(10,2);
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(p_customer_id INT, p_initial_balance DECIMAL(10,2)) IS
    BEGIN
        INSERT INTO Accounts (CustomerID, Balance)
        VALUES (p_customer_id, p_initial_balance);
    END OpenAccount;

    PROCEDURE CloseAccount(p_account_id INT) IS
    BEGIN
        DELETE FROM Accounts
        WHERE AccountID = p_account_id;
    END CloseAccount;

    FUNCTION GetTotalBalance(p_customer_id INT) RETURN DECIMAL(10,2) IS
        v_total_balance DECIMAL(10,2);
    BEGIN
        SELECT SUM(Balance) INTO v_total_balance
        FROM Accounts
        WHERE CustomerID = p_customer_id;
        
        RETURN v_total_balance;
    END GetTotalBalance;

END AccountOperations;
/
