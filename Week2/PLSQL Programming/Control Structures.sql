-- Scenario 1
DECLARE
    v_customer Customers%ROWTYPE;
    v_loan Loans%ROWTYPE;
BEGIN
    FOR v_customer IN (SELECT * FROM Customers) LOOP
        IF TRUNC(MONTHS_BETWEEN(SYSDATE, v_customer.DOB) / 12) > 60 THEN
            FOR v_loan IN (SELECT * FROM Loans WHERE CustomerID = v_customer.CustomerID) LOOP
                UPDATE Loans
                SET InterestRate = InterestRate - 0.01
                WHERE LoanID = v_loan.LoanID;
            END LOOP;
        END IF;
    END LOOP;
    COMMIT;
END;

-- Scenario 2
ALTER TABLE Customers
ADD IsVIP VARCHAR2(5) DEFAULT 'FALSE';

DECLARE
    v_customer Customers%ROWTYPE;
BEGIN
    FOR v_customer IN (SELECT * FROM Customers) LOOP
        IF v_customer.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = v_customer.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;

-- Scenario 3
DECLARE
    v_loan Loans%ROWTYPE;
    v_customer Customers%ROWTYPE;
BEGIN
    FOR v_loan IN (SELECT * FROM Loans WHERE EndDate <= SYSDATE + 30) LOOP
        SELECT * INTO v_customer
        FROM Customers
        WHERE CustomerID = v_loan.CustomerID;
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan ' || v_loan.LoanID || ' for customer ' || v_customer.Name || ' is due on ' || v_loan.EndDate);
    END LOOP;
END;
