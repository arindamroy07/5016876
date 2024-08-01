-- Scenario 1
DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.CustomerID, c.Name, t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Accounts a ON c.CustomerID = a.CustomerID
        JOIN Transactions t ON a.AccountID = t.AccountID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSTIMESTAMP)
        ORDER BY c.CustomerID, t.TransactionDate;

    v_customer_id NUMBER;
    v_name VARCHAR2(100);
    v_transaction_date DATE;
    v_amount NUMBER;
    v_transaction_type VARCHAR2(100);

BEGIN
    OPEN GenerateMonthlyStatements;
    LOOP
        FETCH GenerateMonthlyStatements INTO v_customer_id, v_name, v_transaction_date, v_amount, v_transaction_type;
        EXIT WHEN GenerateMonthlyStatements%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || v_customer_id);
        DBMS_OUTPUT.PUT_LINE('Name: ' || v_name);
        DBMS_OUTPUT.PUT_LINE('Transaction Date: ' || v_transaction_date);
        DBMS_OUTPUT.PUT_LINE('Amount: ' || v_amount);
        DBMS_OUTPUT.PUT_LINE('Transaction Type: ' || v_transaction_type);
        DBMS_OUTPUT.PUT_LINE('Thank you. Vist again');
        DBMS_OUTPUT.PUT_LINE('');
    END LOOP;
    CLOSE GenerateMonthlyStatements;
END;

-- Scenario 2
DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance
        FROM Accounts
        FOR UPDATE;

    v_account_id NUMBER;
    v_balance NUMBER;
    v_annual_fee NUMBER := 150;

BEGIN
    OPEN ApplyAnnualFee;
    LOOP
        FETCH ApplyAnnualFee INTO v_account_id, v_balance;
        EXIT WHEN ApplyAnnualFee%NOTFOUND;

        IF v_balance >= v_annual_fee THEN
            UPDATE Accounts
            SET Balance = Balance - v_annual_fee
            WHERE CURRENT OF ApplyAnnualFee;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Insufficient balance in account ' || v_account_id);
        END IF;
    END LOOP;
    CLOSE ApplyAnnualFee;
END;

-- Scenario 3
DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate
        FROM Loans
        FOR UPDATE;

    v_loan_id NUMBER;
    v_interest_rate NUMBER;
    v_new_interest_rate NUMBER;

BEGIN
    OPEN UpdateLoanInterestRates;
    LOOP
        FETCH UpdateLoanInterestRates INTO v_loan_id, v_interest_rate;
        EXIT WHEN UpdateLoanInterestRates%NOTFOUND;

        v_new_interest_rate := v_interest_rate + 0.01;

        UPDATE Loans
        SET InterestRate = v_new_interest_rate
        WHERE CURRENT OF UpdateLoanInterestRates;
    END LOOP;
    CLOSE UpdateLoanInterestRates;
END;
