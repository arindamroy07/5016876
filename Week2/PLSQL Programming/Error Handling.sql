-- Scenario 1
CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_account_id NUMBER,
    p_to_account_id NUMBER,
    p_amount NUMBER
) AS
BEGIN
    DECLARE
        v_insufficient_funds EXCEPTION;
        v_from_balance NUMBER;
        v_to_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_from_balance
        FROM Accounts
        WHERE AccountID = p_from_account_id;
        
        IF v_from_balance < p_amount THEN
            RAISE v_insufficient_funds;
        END IF;
        
        UPDATE Accounts
        SET Balance = Balance - p_amount
        WHERE AccountID = p_from_account_id;
        
        UPDATE Accounts
        SET Balance = Balance + p_amount
        WHERE AccountID = p_to_account_id;
        
        COMMIT;
    EXCEPTION
        WHEN v_insufficient_funds THEN
            DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in account ' || p_from_account_id);
            ROLLBACK;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
            ROLLBACK;
    END;
END;

BEGIN
    SafeTransferFunds(1, 2, 500);
END;

-- Scenario 2
CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id NUMBER,
    p_percentage NUMBER
) AS
BEGIN
    DECLARE
        v_invalid_employee EXCEPTION;
        v_current_salary NUMBER;
    BEGIN
        SELECT Salary INTO v_current_salary
        FROM Employees
        WHERE EmployeeID = p_employee_id;
        
        IF v_current_salary IS NULL THEN
            RAISE v_invalid_employee;
        END IF;
        
        UPDATE Employees
        SET Salary = Salary + (Salary * (p_percentage / 100))
        WHERE EmployeeID = p_employee_id;
        
        COMMIT;
    EXCEPTION
        WHEN v_invalid_employee THEN
            DBMS_OUTPUT.PUT_LINE('Error: Employee ' || p_employee_id || ' does not exist');
            ROLLBACK;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
            ROLLBACK;
    END;
END;

BEGIN
    UpdateSalary(2, 10);
END;

-- Scenario 3
CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_customer_id NUMBER,
    p_name VARCHAR2,
    p_dob DATE,
    p_balance NUMBER
) AS
BEGIN
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance)
        VALUES (p_customer_id, p_name, p_dob, p_balance);
        
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer ' || p_customer_id || ' already exists');
            ROLLBACK;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
            ROLLBACK;
    END;
END;

-- For duplicate
BEGIN
    AddNewCustomer(1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000);
END;

-- For new
BEGIN
    AddNewCustomer(3, 'Jon Cena', TO_DATE('1980-05-20', 'YYYY-MM-DD'), 6000);
END;
