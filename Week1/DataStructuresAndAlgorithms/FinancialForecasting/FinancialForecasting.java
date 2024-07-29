package Week1.DataStructuresAndAlgorithms.FinancialForecasting;

// Explain the concept of recursion and how it can simplify certain problems.
// Recursion is a method where a function calls itself to solve smaller instances of the same problem
// It simplifies problems by breaking them down into more manageable sub-problems

public class FinancialForecasting {
    public static double calculateFutureValue(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, years - 1);
    }

    public static void main(String[] args) {
        double futureValue = calculateFutureValue(1000, 0.095, 5);
        System.out.println("Future Value: " + futureValue);
    }
}

// Discuss the time complexity of your recursive algorithm.
// Time complexity: O(n) where n is the number of years

// Explain how to optimize the recursive solution to avoid excessive computation
// To avoid excessive computation we can use 'memoization' to store already computed values. This technique saves the results of expensive function calls and reuses them when the same inputs occur again
