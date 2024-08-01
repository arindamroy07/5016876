-- Scenario 1
CREATE OR REPLACE FUNCTION CalculateAge(
    p_dob DATE
) RETURN NUMBER AS
    v_age NUMBER;
BEGIN
    v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END;

SELECT CalculateAge(TO_DATE('1985-05-15', 'YYYY-MM-DD')) AS age FROM dual;

-- Scenario 2
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount NUMBER,
    p_interest_rate NUMBER,
    p_loan_duration NUMBER
) RETURN NUMBER AS
    v_monthly_interest_rate NUMBER;
    v_monthly_installment NUMBER;
BEGIN
    v_monthly_interest_rate := p_interest_rate / 12;
    v_monthly_installment := p_loan_amount * v_monthly_interest_rate * POWER(1 + v_monthly_interest_rate, p_loan_duration * 12) / (POWER(1 + v_monthly_interest_rate, p_loan_duration * 12) - 1);
    RETURN ROUND(v_monthly_installment);
END;

SELECT CalculateMonthlyInstallment(60000, 3, 12) AS monthly_installment FROM dual;

-- Scenario 3
CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id NUMBER,
    p_amount NUMBER
) RETURN BOOLEAN AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_account_id;
    
    RETURN v_balance >= p_amount;
END;

-- call
DECLARE
  l_account_id NUMBER := 1;
  l_amount NUMBER := 2000;
  l_has_sufficient_balance BOOLEAN;
BEGIN
  l_has_sufficient_balance := HasSufficientBalance(l_account_id, l_amount);
  IF l_has_sufficient_balance THEN
    DBMS_OUTPUT.PUT_LINE('Account has sufficient balance');
  ELSE
    DBMS_OUTPUT.PUT_LINE('Account does not have sufficient balance');
  END IF;
END;
