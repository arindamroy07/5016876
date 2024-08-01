-- Scenario 1
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
AFTER UPDATE ON Customers
FOR EACH ROW
BEGIN
    UPDATE Customers
    SET LastModified = SYSTIMESTAMP
    WHERE CustomerID = :NEW.CustomerID;
END;

-- Scenario 2
CREATE SEQUENCE AuditLog_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE TABLE AuditLog (
    LogID NUMBER PRIMARY KEY,
    TransactionID NUMBER,
    LogDate DATE,
    LogMessage VARCHAR2(200)
);

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (LogID, TransactionID, LogDate, LogMessage)
    VALUES (AuditLog_SEQ.NEXTVAL, :NEW.TransactionID, SYSTIMESTAMP, 'Transaction inserted');
END;

-- Scenario 3
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = :NEW.AccountID;
    
    IF :NEW.TransactionType = 'Withdrawal' AND :NEW.Amount > v_balance THEN
        DBMS_OUTPUT.PUT_LINE('Insufficient balance for withdrawal');
    ELSIF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        DBMS_OUTPUT.PUT_LINE('Deposit amount must be positive');
    END IF;
END;
