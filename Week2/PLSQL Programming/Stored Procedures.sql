-- Scenario 1
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest(
    p_account_type VARCHAR2
) AS
BEGIN
    UPDATE Accounts
    SET Balance = Balance - (Balance * 0.01)
    WHERE AccountType = p_account_type;
    
    COMMIT;
END;

BEGIN
    ProcessMonthlyInterest('Savings');
END;

-- Scenario 2
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department VARCHAR2,
    p_bonus_percentage NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * (p_bonus_percentage / 100))
    WHERE Department = p_department;
    
    COMMIT;
END;

BEGIN
    UpdateEmployeeBonus('HR', 10);
END;

-- Scenario 3
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account_id NUMBER,
    p_to_account_id NUMBER,
    p_amount NUMBER
) AS
    v_from_balance NUMBER;
    v_to_balance NUMBER;
BEGIN
    SELECT Balance INTO v_from_balance
    FROM Accounts
    WHERE AccountID = p_from_account_id;
    
    IF v_from_balance < p_amount THEN
        DBMS_OUTPUT.PUT_LINE('Insufficient balance in account');
    END IF;
    
    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_from_account_id;
    
    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_to_account_id;
    
    COMMIT;
END;

BEGIN
    TransferFunds(2, 1, 600);
END;
