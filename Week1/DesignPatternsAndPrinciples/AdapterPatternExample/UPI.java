package Week1.DesignPatternsAndPrinciples.AdapterPatternExample;

public class UPI {
    public void payWithUPI(String paymentMethod, double amount) {
        System.out.println("Paid $" + amount + " using UPI Transfer with " + paymentMethod);
    }
}
